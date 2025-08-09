package com.onlineshopping.model;

public class Product {
    private int productId;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(int productId, String name, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters and Setters

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }

    public void setStockQuantity(int quantity) {
        this.stockQuantity = quantity;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", name=" + name +
                ", price=" + price + ", stockQuantity=" + stockQuantity + "]";
    }
}
