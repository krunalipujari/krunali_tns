package com.tnsif.markerinterface;

public class MarkerInterface {
	
	public static void main(String[] args)
	{
		Student s = new Student(101,"Shra",12000,"java");
		
		if(s instanceof Registrable)
		{
			System.out.println("Student is registerd for the course");
		}
		else
		{
			System.out.println("Student is not registerd for the course");
		}
		
	}

}
