package com.oms.userMS.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seller")
public class Seller {
	@Id
	@Column(name = "seller_id", nullable = false, length = 20)
	String sellerID;
	@Column(nullable = false, length = 20)
	String name;
	@Column(name = "email", nullable = false, length = 30)
	String emailID;
	@Column(name = "phone_number", nullable = false, length = 10)
	String phoneNo;
	@Column(nullable = false, length = 20)
	String password;
	@Column(name = "is_active", nullable = false, length = 5)
	String isActive;
	
	public String getSellerID() {
		return sellerID;
	}

	public void setSellerID(String sellerID) {
		this.sellerID = sellerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Seller() {
		super();
	}
}
