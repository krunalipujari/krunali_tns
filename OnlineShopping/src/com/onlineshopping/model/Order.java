package com.onlineshopping.model;

import java.util.List;

public class Order {
    private static int count = 1;
    private int orderId;
    private Customer customer;
    private List<ProductQuantityPair> products;
    private String status;

    public Order(Customer customer, List<ProductQuantityPair> products) {
        this.orderId = count++;
        this.customer = customer;
        this.products = products;
        this.status = "Pending";
    }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public List<ProductQuantityPair> getProducts() { return products; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
