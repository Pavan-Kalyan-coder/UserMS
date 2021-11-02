package com.oms.userMS.dto;

import com.oms.userMS.entity.WishList;

public class WishListDTO {
	String buyerID;
	String productID;
	
	public WishList createWishListEntity(WishListDTO wishListDTO) {
		WishList wishList = new WishList();
		wishList.setProductID(wishListDTO.getProductID());
		wishList.setBuyerID(wishListDTO.getBuyerID());
		wishList.setProductID(wishListDTO.getProductID());
		return wishList;
	}
	public WishListDTO createWishListDTO(WishList wishList) {
		WishListDTO wishListDTO = new WishListDTO();
		wishListDTO.setBuyerID(wishList.getBuyerID());
		wishListDTO.setProductID(wishList.getProductID());
		return wishListDTO;
	}
	
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
}
