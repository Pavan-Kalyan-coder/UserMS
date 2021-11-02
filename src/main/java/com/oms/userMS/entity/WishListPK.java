package com.oms.userMS.entity;

import java.io.Serializable;
import java.util.Objects;

public class WishListPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String buyerID;
    private String productID;
    public WishListPK() {}
    public WishListPK(String buyerID, String productID) {
		this.buyerID = buyerID;
		this.productID = productID;
	}
    
    // equals, hashCode    
    
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(buyerID, productID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WishListPK other = (WishListPK) obj;
		return Objects.equals(buyerID, other.buyerID) && Objects.equals(productID, other.productID);
	}
	
}
