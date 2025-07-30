package com.tnsif.ExceptionHandling;

public class WithoutExceptionHandling {
	public static void main(String[] args) {
		
		System.out.println("The program continues......");
		
		try {
	 int div = 100/0;
	 System.out.println("Div = "+div);
		}
		
		catch(ArithmeticException a)
		{
		System.out.println(	a.getMessage());
		}
		
	System.out.println("Exception Handling");
		System.out.println("Number divided by zero");
		
		
				
		
	}

}
