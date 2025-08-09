package com.onlineshopping.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private String address;
    private ShoppingCart cart;
    private List<Order> orders = new ArrayList<>();

    public Customer(int userId, String username, String email, String address) {
        super(userId, username, email);
        this.address = address;
        this.cart = new ShoppingCart();
    }

    public String getAddress() { return address; }
    public ShoppingCart getCart() { return cart; }
    public List<Order> getOrders() { return orders; }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
