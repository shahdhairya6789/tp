package com.bean;

public class Employee {

	private String firstName,lastName;
	private int id;
	
	public String getLastName() {
		System.out.println("Getter LastName");
		return lastName;
	}

	public void setLastName(String lastName) {
		System.out.println("Setter lastName");
		this.lastName = lastName;
	}

	public Employee() {
		// TODO Auto-generated constructor stub
		System.out.println("Employee Constructor");
	}
	
	public void initUser()
	{
		System.out.println("init");
	}
	
	public void destroy()
	{
		System.out.println("destroy");
	}
	
	public String getFirstName() {
		System.out.println("Getter firstName");
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		System.out.println("setter firstNAme");
		this.firstName = firstName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	
}
