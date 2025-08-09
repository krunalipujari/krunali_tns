package com.onlineshopping;

import com.onlineshopping.model.*;
import com.onlineshopping.service.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();
        AdminService adminService = new AdminService();
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Admin Menu\n2. Customer Menu\n3. Exit");
            int ch = sc.nextInt();
            if (ch == 1) {
                adminMenu(sc, productService, adminService, orderService);
            } else if (ch == 2) {
                customerMenu(sc, productService, customerService, orderService);
            } else {
                System.out.println("Exiting...");
                break;
            }
        }
        sc.close();
    }

    public static void adminMenu(Scanner sc, ProductService ps, AdminService as, OrderService os) {
        while (true) {
            System.out.println("\nAdmin Menu:\n1. Add Product\n2. Remove Product\n3. View Products\n4. Create Admin\n5. View Admins\n6. Update Order Status\n7. View Orders\n8. Return");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int pid = sc.nextInt();
                    System.out.print("Enter Product Name: ");
                    String pname = sc.next();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    ps.addProduct(new Product(pid, pname, price, qty));
                    System.out.println("Product added successfully!");
                    break;
                case 2:
                    System.out.print("Enter Product ID to remove: ");
                    pid = sc.nextInt();
                    ps.removeProduct(pid);
                    System.out.println("Product removed.");
                    break;
                case 3:
                    ps.getAllProducts().forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Enter Admin ID: ");
                    int aid = sc.nextInt();
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Email: ");
                    String email = sc.next();
                    as.addAdmin(new Admin(aid, name, email));
                    System.out.println("Admin created!");
                    break;
                case 5:
                    as.getAllAdmins().forEach(a ->
                        System.out.println("ID: " + a.getUserId() + ", Name: " + a.getUsername() + ", Email: " + a.getEmail()));
                    break;
                case 6:
                    System.out.print("Enter Order ID: ");
                    int oid = sc.nextInt();
                    System.out.print("Enter new status (Completed/Delivered/Cancelled): ");
                    String status = sc.next();
                    os.updateOrderStatus(oid, status);
                    os.viewAllOrders();
                    break;

                case 7:
                    os.viewAllOrders();  // ✅ this will fetch and display all orders from the DB
                    break;

                case 8:
                    return;
            }
        }
    }

    public static void customerMenu(Scanner sc, ProductService ps, CustomerService cs, OrderService os) {
        while (true) {
            System.out.println("\nCustomer Menu:\n1. Create Customer\n2. View Customers\n3. Place Order\n4. View Orders\n5. View Products\n6. Return");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.print("Enter ID: ");
                    int cid = sc.nextInt();
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Email: ");
                    String email = sc.next();
                    System.out.print("Enter Address: ");
                    String addr = sc.next();
                    cs.addCustomer(new Customer(cid, name, email, addr));
                    System.out.println("Customer created.");
                    break;
                case 2:
                    cs.getAllCustomers().forEach(c ->
                        System.out.println("ID: " + c.getUserId() + ", Name: " + c.getUsername() + ", Address: " + c.getAddress()));
                    break;
                case 3:
                    System.out.print("Enter Customer ID: ");
                    cid = sc.nextInt();
                    Customer cust = cs.getCustomerById(cid);
                    if (cust != null) {
                        while (true) {
                            System.out.print("Enter Product ID to add (-1 to finish): ");
                            int pid = sc.nextInt();
                            if (pid == -1) break;
                            Product prod = ps.getProductById(pid);
                            if (prod != null) {
                                System.out.print("Enter Quantity: ");
                                int qty = sc.nextInt();
                                cust.getCart().addProduct(prod, qty);
                            } else {
                                System.out.println("Product not found.");
                            }
                        }
                        os.placeOrder(cust);
                        System.out.println("Order placed successfully!");
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Customer ID: ");
                    cid = sc.nextInt();
                    Customer customer = cs.getCustomerById(cid);
                    if (customer != null) {
                        for (Order o : customer.getOrders()) {
                            System.out.println("Order ID: " + o.getOrderId() + ", Status: " + o.getStatus());
                            for (ProductQuantityPair p : o.getProducts()) {
                                System.out.println("  Product: " + p.getProduct().getName() + ", Quantity: " + p.getQuantity());
                            }
                        }
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case 5:
                    ps.getAllProducts().forEach(System.out::println);
                    break;
                case 6:
                    return;
            }
        }
    }
}
