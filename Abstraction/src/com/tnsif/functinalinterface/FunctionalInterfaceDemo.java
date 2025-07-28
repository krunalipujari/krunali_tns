package com.tnsif.functinalinterface;

public class FunctionalInterfaceDemo {
	
	public static void main(String[] args) {
		
		GreetClass g = new GreetClass();
		System.out.println(g.greet());
		
		//using lambda expression
		
		GreetInterface g1 = () -> {
			return "Good Afternoon";
		};
		
		System.out.println(g1.greet());
	}

}
