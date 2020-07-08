package com.bean;

public class Employee1 {

	private String firstName;

	public Employee1()
	{
		System.out.println("Employee1 Constructor");
	}
	
	public String getFirstName() {
		System.out.println("Employee1");
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		System.out.println("Employee1");
	}
	
}
