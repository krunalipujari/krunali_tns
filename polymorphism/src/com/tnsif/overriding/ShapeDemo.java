package com.tnsif.overriding;

public class ShapeDemo {

    public static void main(String[] args) {

        Shape[] shapes = new Shape[2];

        shapes[0] = new Circle(5.0);  
        shapes[1] = new Square(6.0);  

        for (Shape shape : shapes) {
            shape.draw();
            shape.erase();
            System.out.println("-------------------");
        }
    }
}
