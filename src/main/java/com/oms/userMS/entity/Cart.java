package com.oms.userMS.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(CartPK.class)
@Table(name="cart")
public class Cart {
	@Id
	@Column(name = "buyer_id", nullable = false)
	String buyerID;
	@Id
	@Column(name = "product_id", nullable = false)
	String productID;
	@Column(nullable = false)
	int quantity;
	
	

	public String getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(String buyerID) {
		this.buyerID = buyerID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
