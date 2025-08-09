package com.onlineshopping.service;

import com.onlineshopping.model.*;
import com.onlineshopping.util.DBConnection;

import java.sql.*;
import java.util.*;

public class OrderService {
    public Order placeOrder(Customer customer) {
        Map<Product, Integer> cartItems = customer.getCart().getItems();
        if (cartItems.isEmpty()) return null;

        List<ProductQuantityPair> orderDetails = new ArrayList<>();
        int generatedOrderId = -1;

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Transaction start

            // 1. Insert into Orders table
            String insertOrderSQL = "INSERT INTO Orders (customerId, status) VALUES (?, ?)";
            try (PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS)) {
                orderStmt.setInt(1, customer.getUserId());
                orderStmt.setString(2, "Pending");
                orderStmt.executeUpdate();
                ResultSet keys = orderStmt.getGeneratedKeys();
                if (keys.next()) {
                    generatedOrderId = keys.getInt(1);
                }
            }

            // 2. Insert into OrderDetails and update Product stock
            String insertDetailsSQL = "INSERT INTO OrderDetails (orderId, productId, quantity) VALUES (?, ?, ?)";
            String updateStockSQL = "UPDATE Product SET stockQuantity = stockQuantity - ? WHERE productId = ?";

            try (PreparedStatement detailStmt = conn.prepareStatement(insertDetailsSQL);
                 PreparedStatement stockStmt = conn.prepareStatement(updateStockSQL)) {

                for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
                    Product product = entry.getKey();
                    int quantity = entry.getValue();

                    if (product.getStockQuantity() < quantity) {
                        System.out.println("Insufficient stock for: " + product.getName());
                        conn.rollback();
                        return null;
                    }

                    // Add to OrderDetails
                    detailStmt.setInt(1, generatedOrderId);
                    detailStmt.setInt(2, product.getProductId());
                    detailStmt.setInt(3, quantity);
                    detailStmt.addBatch();

                    // Update stock
                    stockStmt.setInt(1, quantity);
                    stockStmt.setInt(2, product.getProductId());
                    stockStmt.addBatch();

                    orderDetails.add(new ProductQuantityPair(product, quantity));
                }

                detailStmt.executeBatch();
                stockStmt.executeBatch();
            }

            conn.commit(); // Transaction commit
            Order order = new Order(customer, orderDetails);
            order.setStatus("Pending");
            customer.getCart().clear();
            System.out.println("Order placed successfully with ID: " + generatedOrderId);
            return order;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateOrderStatus(int orderId, String newStatus) {
        String sql = "UPDATE Orders SET status = ? WHERE orderId = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, orderId);
            ps.executeUpdate();
            System.out.println("Order status updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAllOrders() {
        String sql = "SELECT o.orderId, o.status, c.username, d.productId, d.quantity, p.name " +
                     "FROM Orders o " +
                     "JOIN Customer c ON o.customerId = c.userId " +
                     "JOIN OrderDetails d ON o.orderId = d.orderId " +
                     "JOIN Product p ON d.productId = p.productId " +
                     "ORDER BY o.orderId";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            int currentOrderId = -1;
            while (rs.next()) {
                int orderId = rs.getInt("orderId");
                if (orderId != currentOrderId) {
                    currentOrderId = orderId;
                    System.out.println("\nOrder ID: " + orderId +
                                       ", Customer: " + rs.getString("username") +
                                       ", Status: " + rs.getString("status"));
                }
                System.out.println("  Product: " + rs.getString("name") +
                                   ", Quantity: " + rs.getInt("quantity"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
