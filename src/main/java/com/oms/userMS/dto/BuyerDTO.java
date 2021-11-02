package com.oms.userMS.dto;

import com.oms.userMS.entity.Buyer;

public class BuyerDTO {
	
	String buyerID;
	String name;
	String emailID;
	String phoneNo;
	String password;
	String isPrivileged;
	String rewardPoints;
	String isActive;
	
	public static Buyer createBuyerEntity(BuyerDTO buyerDTO) {
		Buyer buyer = new Buyer();
		buyer.setBuyerID(buyerDTO.getBuyerID());
		buyer.setName(buyerDTO.getName());
		buyer.setEmailID(buyerDTO.getEmailID());
		buyer.setPhoneNo(buyerDTO.getPhoneNo());
		buyer.setPassword(buyerDTO.getPassword());
		buyer.setIsPrivileged(buyerDTO.getIsPrivileged());
		buyer.setRewardPoints(buyerDTO.getRewardPoints());
		buyer.setIsActive(buyerDTO.getIsActive());
		return buyer;
	}
	public static BuyerDTO createBuyerDTO(Buyer buyer) {
		BuyerDTO buyerDTO = new BuyerDTO();
		buyerDTO.setBuyerID(buyer.getBuyerID());
		buyerDTO.setName(buyer.getName());
		buyerDTO.setEmailID(buyer.getEmailID());
		buyerDTO.setPhoneNo(buyer.getPhoneNo());
		buyerDTO.setPassword(buyer.getPassword());
		buyerDTO.setIsPrivileged(buyer.getIsPrivileged());
		buyerDTO.setRewardPoints(buyer.getRewardPoints());
		buyerDTO.setIsActive(buyer.getIsActive());
		return buyerDTO;
	}
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


}
