package com.tnsif.overloading;

public class ticketbooking {
	
	private String stageEvent;
	private String customer;
	private int noOfSeats;
	
	public ticketbooking(String stageEvent, String customer, int noOfSeats) {
		
		this.stageEvent = stageEvent;
		this.customer = customer;
		this.noOfSeats = noOfSeats;
	}
	
	public String getStageEvent() {
		return stageEvent;
	}
	
	public void setStageEvent(String stageEvent) {
		this.stageEvent = stageEvent;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	
	public void makePayment(Double amount)
	{
		System.out.println("Amount " + amount + " paid in cash");
	}
	
	public void makePayment(String walletNumber,Double amount)
	{
		System.out.println("Amount " + amount + " paid in using wallet number " + walletNumber);
	}
	
	public void makePayment(String creditCard,String ccv,String name,Double amount)
	{
		System.out.println("Holder Name: " + name);
		System.out.println("Amount " + amount + " paid using " + creditCard);
		System.out.println("CCV: " +ccv);
	}

}