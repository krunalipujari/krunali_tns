package com.tnsif.abstrction;

public class Rectangle extends Shape{
	
	private float width,height;
	
	public Rectangle()
	{
		this.width = 5.0f;
		this.height = 2.0f;
		
	}
	
	public Rectangle(float width, float height) {
		super();
		this.width=width;
		this.height=height;
		
	}
	
	void calArea() {
		super.area = width*height;
		
	}
	

}
