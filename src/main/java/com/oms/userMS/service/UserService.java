package com.oms.userMS.service;

import java.util.List;

import com.oms.userMS.dto.BuyerDTO;
import com.oms.userMS.dto.CartDTO;
import com.oms.userMS.dto.LoginDTO;
import com.oms.userMS.dto.SellerDTO;
import com.oms.userMS.exception.UserException;

public interface UserService {

	String createBuyer(BuyerDTO buyerDTO) throws UserException;

	String buyerLogin(LoginDTO loginDTO) throws UserException;

	String inactivateBuyer(String buyerID) throws UserException;

	String deleteBuyer(String buyerID) throws UserException;

	String addToWishList(String buyerID, String productID);

	String removeFromWishList(String buyerID, String productID);

	String addToCart(String buyerID, String productID, int quantity);

	String removeFromCart(String buyerID, String productID);

	String updateQuantityCart(String buyerID, String productID, int quantity);

	List<CartDTO> getCartProducts(String buyerID) throws UserException;

	int getRewardPoints(String buyerID);

	String buyerBecomePrivileged(String buyerID);

	String addRewardPoints(String buyerID, String rewardPoints);

	String createSeller(SellerDTO sellerDTO) throws UserException;

	String sellerLogin(LoginDTO loginDTO) throws UserException;

	String inactivateSeller(String sellerID) throws UserException;

	String deleteSeller(String sellerID) throws UserException;

	String removeFromCartByProduct(String productID);
	
	String removeFromWishListByProduct(String productID);

	int getQuantityByBuyerIDAndProductID(String buyerID, String productID);
}
