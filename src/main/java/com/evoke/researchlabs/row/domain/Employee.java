package com.evoke.researchlabs.row.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @description EmployeeBean class act as a Pojo class
 * @author P A VISHAL
 * @version 1.0
 * 
 */
@Entity
public class Employee implements Serializable{ 
	 
	static int instanceCounter = 0;

int counter;
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 
	 public int id;
	
	
	public Employee(){
		
		instanceCounter++;

		counter = instanceCounter;
	}
	 
	public static int getInstanceCounter() {
		return instanceCounter;
	}

	public static void setInstanceCounter(int instanceCounter) {
		Employee.instanceCounter = instanceCounter;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String userName;

	private String emailId;

	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Employee [userName=" + userName + " emailId=" + emailId  +"]";
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	

}