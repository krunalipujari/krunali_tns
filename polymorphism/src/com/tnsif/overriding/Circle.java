package com.tnsif.overriding;

public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Circle with radius: " + radius);
    }

    @Override
    public void erase() {
        System.out.println("Erasing the Circle with radius: "+ radius);
    }
}
