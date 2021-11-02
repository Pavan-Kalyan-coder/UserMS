package com.oms.userMS.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="buyer")
public class Buyer {
	@Id
	@Column(name = "buyer_id", nullable = false, length = 20)
	String buyerID;
	@Column(nullable = false, length = 20)
	String name;
	@Column(name = "email", nullable = false, length = 20)
	String emailID;
	@Column(name = "phone_number", nullable = false, length = 10)
	String phoneNo;
	@Column(nullable = false, length = 20)
	String password;
	@Column(nullable = false, length = 5)
	String isPrivileged;
	@Column(nullable = false, length = 20)
	String rewardPoints;
	@Column(nullable = false, length = 5)
	String isActive;
	
	public String getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(String buyerID) {
		this.buyerID = buyerID;
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

	public String getIsPrivileged() {
		return isPrivileged;
	}

	public void setIsPrivileged(String isPrivileged) {
		this.isPrivileged = isPrivileged;
	}

	public String getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(String rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Buyer() {
		super();
	}
}
