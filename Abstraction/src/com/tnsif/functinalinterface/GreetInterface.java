
//program to demonstrate Functional Interface -Greet Interface
package com.tnsif.functinalinterface;

@FunctionalInterface
public interface GreetInterface {
	
	public String greet();
	
	
	
	//default method
	 default void method1()
	 {
		 System.out.println("Default method");
	 }
	 
	 //Static method
	 static void method2() 
	 {
		 System.out.println("Static method"); 
	 }

}
