package com.oms.userMS.entity;

import java.io.Serializable;

public class CartPK implements Serializable{
//	@Override
//	public int hashCode() {
//		return Objects.hash(buyerID, productID);
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		CartPK other = (CartPK) obj;
//		return Objects.equals(buyerID, other.buyerID) && Objects.equals(productID, other.productID);
//	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String buyerID;
    private String productID;
    public CartPK() {}
    public CartPK(String buyerID, String productID) {
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
}
