package com.oms.userMS.dto;

import com.oms.userMS.entity.Cart;

public class CartDTO {
	String buyerID;
	String productID;
	int quantity;
	
	public static Cart createCartEntity(CartDTO cartDTO)
	{
		Cart cart = new Cart();
		cart.setBuyerID(cartDTO.getBuyerID());
		cart.setProductID(cartDTO.getProductID());
		cart.setQuantity(cartDTO.getQuantity());
		return cart;
	}
	public static CartDTO createCartDTO(Cart cart)
	{
		CartDTO cartDTO = new CartDTO();
		cartDTO.setBuyerID(cart.getBuyerID());
		cartDTO.setProductID(cart.getProductID());
		cartDTO.setQuantity(cart.getQuantity());
		return cartDTO;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
