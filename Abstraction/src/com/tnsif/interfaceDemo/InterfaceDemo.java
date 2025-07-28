package com.tnsif.interfaceDemo;

public interface InterfaceDemo {
	
	//abstract
	 void show();
	 
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
