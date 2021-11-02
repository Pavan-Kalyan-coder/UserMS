package com.oms.userMS.dto;

import com.oms.userMS.entity.Seller;

public class SellerDTO {
	String sellerID;
	String name;
	String emailID;
	String phoneNo;
	String password;
	String isActive;
	public Seller createSellerEntity(SellerDTO sellerDTO) {
		Seller seller = new Seller();
		seller.setSellerID(sellerDTO.getSellerID());
		seller.setName(sellerDTO.getName());
		seller.setEmailID(sellerDTO.getEmailID());
		seller.setPhoneNo(sellerDTO.getPhoneNo());
		seller.setIsActive(sellerDTO.getIsActive());
		return seller;
	}
	public SellerDTO createSellerDTO(Seller seller) {
		SellerDTO sellerDTO = new SellerDTO();
		sellerDTO.setSellerID(seller.getSellerID());
		sellerDTO.setName(seller.getName());
		sellerDTO.setEmailID(seller.getEmailID());
		sellerDTO.setPhoneNo(seller.getPhoneNo());
		sellerDTO.setIsActive(seller.getIsActive());
		return sellerDTO;
	}
	
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
	
}
