package com.tnsif.markerinterface;

public abstract class Teacher implements Data , Registrable{
	
	private int id;
	private String name;
	
	public Teacher() {
		
	}

	public Teacher(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + "]";
	}
	
	
	

}
