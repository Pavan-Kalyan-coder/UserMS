package com.oms.userMS.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(WishListPK.class)
@Table(name="wishlist")
public class WishList {
	@Id
	@Column(name = "buyer_id", nullable = false)
	String buyerID;
	@Id
	@Column(name = "product_id", nullable = false)
	String productID;
	
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
	public WishList() {
		super();
	}
}
