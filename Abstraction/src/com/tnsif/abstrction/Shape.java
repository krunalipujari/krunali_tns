package com.tnsif.abstrction;

abstract public class Shape {
	
	protected float area;
	
	
	//abstract method
	abstract void calArea();
	
	void show()
	{
		System.out.println("Area of shape is"+area);
	}

}
