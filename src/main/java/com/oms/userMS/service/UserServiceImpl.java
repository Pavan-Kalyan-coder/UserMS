package com.oms.userMS.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.userMS.dto.BuyerDTO;
import com.oms.userMS.dto.CartDTO;
import com.oms.userMS.dto.LoginDTO;
import com.oms.userMS.dto.SellerDTO;
import com.oms.userMS.entity.Buyer;
import com.oms.userMS.entity.Cart;
import com.oms.userMS.entity.CartPK;
import com.oms.userMS.entity.Seller;
import com.oms.userMS.entity.WishList;
import com.oms.userMS.entity.WishListPK;
import com.oms.userMS.exception.UserException;
import com.oms.userMS.repository.BuyerRepository;
import com.oms.userMS.repository.CartRepository;
import com.oms.userMS.repository.SellerRepository;
import com.oms.userMS.repository.WishListRepository;
import com.oms.userMS.validator.Validator;

@Service
public class UserServiceImpl implements UserService{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BuyerRepository buyerRepo;
	
	@Autowired
	SellerRepository sellerRepo;
	
	@Autowired
	WishListRepository wishListRepo;
	
	@Autowired
	CartRepository cartRepo;
	
	//Buyer Register
	public String createBuyer(BuyerDTO buyerDTO) throws UserException{
		logger.info("Creation request for buyer {}", buyerDTO);
		
		Validator.validateBuyer(buyerDTO);
		
		if(buyerRepo.findByEmailID(buyerDTO.getEmailID()).orElse(null) != null)
		{
			throw new UserException("email already in use by other buyer");
		}
		if(buyerRepo.findByPhoneNo(buyerDTO.getPhoneNo()).orElse(null) != null)
		{
			throw new UserException("Phone Number already in use by other buyer");
		}
		
		Buyer buyer = BuyerDTO.createBuyerEntity(buyerDTO);
		buyer.setIsActive("false");
		buyerRepo.save(buyer);
		String result = "Buyer account Successfully created";
		return result;
	}
	//Seller Register
	public String createSeller(SellerDTO sellerDTO) throws UserException {
		logger.info("Creation request for seller {}", sellerDTO);
		Validator.validateSeller(sellerDTO);
		
		if(sellerRepo.findByEmailID(sellerDTO.getEmailID()).orElse(null) != null)
		{
			throw new UserException("email already in use by other seller");
		}
		if(sellerRepo.findByPhoneNo(sellerDTO.getPhoneNo()).orElse(null) != null)
		{
			throw new UserException("Phone Number already in use by other seller");
		}
		
		Seller seller = sellerDTO.createSellerEntity(sellerDTO);
		seller.setIsActive("false");
		sellerRepo.save(seller);
		String result = "Seller account Successfully created";
		return result;
	}
	
	public String buyerLogin(LoginDTO loginDTO) throws UserException {
		
		logger.info("Login request for buyer {}", loginDTO.getEmailID());
		if(!Validator.validateEmail(loginDTO.getEmailID()))
		{
			throw new UserException("Enter valid email id");
		}
		Buyer buyer;
		buyer=buyerRepo.findByEmailID(loginDTO.getEmailID()).orElse(null);
		if(buyer == null)
			throw new UserException("Buyer with this Email doesn't exists in the database");
		
		if(!loginDTO.getPassword().equals(buyer.getPassword()))
			throw new UserException("Wrong Password");
		buyer.setIsActive("true");
		return "Buyer Login Successful"; 
	}

	public String sellerLogin(LoginDTO loginDTO) throws UserException {
		logger.info("Login request for seller {} with password {}", loginDTO.getEmailID(),loginDTO.getPassword());
		
		if(!Validator.validateEmail(loginDTO.getEmailID()))
		{
			throw new UserException("Enter valid email id");
		}
		Seller seller;
		seller=sellerRepo.findByEmailID(loginDTO.getEmailID()).orElse(null);
		
		if(seller == null)
			throw new UserException("Seller with this Email doesn't exists in the database");
		
		if(!seller.getPassword().equals(seller.getPassword()))
			throw new UserException("Wrong Password");
		seller.setIsActive("true");
		return "Seller Login Successful"; 
	}
	public String inactivateBuyer(String buyerID) throws UserException {
		Buyer buyer = (Buyer)buyerRepo.findById(buyerID).orElse(null);
		if(buyer == null)
		{
			throw new UserException("Buyer not found");
		}
		else
		{
			buyer.setIsActive("false");
			buyerRepo.save(buyer);
		}
		return "Buyer Account is successfully inactivated";
	}
	public String inactivateSeller(String sellerID) throws UserException {
		Seller seller = (Seller)sellerRepo.findById(sellerID).orElse(null);
		if(seller == null)
		{
			throw new UserException("Seller not found");
		}
		else
		{
			seller.setIsActive("false");
			sellerRepo.save(seller);
		}
		return "Seller Account is successfully inactivated";
	}
	
	public String deleteBuyer(String buyerID) throws UserException {
		Buyer buyer = buyerRepo.findById(buyerID).orElse(null);
		if(buyer == null)
		{
			throw new UserException("Buyer not found");
		}
		else
		{
			
			List<WishList> wishLists = wishListRepo.findByBuyerID(buyerID);
			if(wishLists.isEmpty() == false)
			{
				for(WishList wishList : wishLists)
				{
					wishListRepo.delete(wishList);
				}
			}
			List<Cart> carts = cartRepo.findByBuyerID(buyerID);
			if(carts.isEmpty() == false)
			{
				for(Cart cart : carts)
				{
					cartRepo.delete(cart);
				}
			}
			
			buyerRepo.delete(buyer);
			
		}
		return "Buyer Account is successfully deleted";
	}
	public String deleteSeller(String sellerID) throws UserException {
		Seller seller = sellerRepo.findById(sellerID).orElse(null);
		if(seller == null)
		{
			throw new UserException("Seller not found");
		}
		else
		{
			sellerRepo.delete(seller);
		}
		return "Seller Account is successfully deleted";
	}
	public String addToWishList(String buyerID, String productID) {
		WishList wishList = new WishList();
		wishList.setBuyerID(buyerID);
		wishList.setProductID(productID);
		wishListRepo.save(wishList);
		return "Added product " + productID + " to wishlist successfully..." ;
	}
	public String removeFromWishList(String buyerID, String productID) {
		//WishList wishList = new WishList();
		//wishList.setBuyerID(buyerID);
		//wishList.setProductID(productID);
		WishList wishList = wishListRepo.findById(new WishListPK(buyerID, productID)).orElse(null);
		if(wishList != null)
		wishListRepo.delete(wishList);
		return "Removed product " + productID + " from wishlist successfully..." ;
	}
	public String removeFromWishListByProduct(String productID) {
		
		List<WishList> wishListsByProduct = wishListRepo.findByProductID(productID);
		for(WishList wishList : wishListsByProduct)
		{
			wishListRepo.delete(wishList);
		}
		return "Removed product " + productID +" From WishLists of all Buyers successfully..." ;
	}
	public String addToCart(String buyerID, String productID, int quantity) {
		Cart cart = new Cart();
		cart.setBuyerID(buyerID);
		cart.setProductID(productID);
		cart.setQuantity(quantity);
		cartRepo.save(cart);
		return "Added product " + productID + " of quantity " + quantity +" To Cart successfully..." ;
	}
	public String removeFromCart(String buyerID, String productID) {
		
		//Cart cart = new Cart();
		//cart.setBuyerID(buyerID);
		//cart.setProductID(productID);
		Cart cart = cartRepo.findById(new CartPK(buyerID, productID)).orElse(null);
		if(cart != null)
		cartRepo.delete(cart);
		return "Removed product " + productID +" From Cart successfully..." ;
	}
	public String removeFromCartByProduct(String productID) {
		
		List<Cart> cartsByProduct = cartRepo.findByProductID(productID);
		for(Cart cart : cartsByProduct)
		{
			cartRepo.delete(cart);
		}
		return "Removed product " + productID +" From Carts of all Buyers successfully..." ;
	}
	public String updateQuantityCart(String buyerID, String productID, int quantity) {
		CartPK cartPK = new CartPK();
		cartPK.setBuyerID(buyerID);
		cartPK.setProductID(productID);
		Cart cart = cartRepo.findById(cartPK).orElse(null);// if not present returns null
		cart.setQuantity(quantity);
		cartRepo.save(cart);
		return "updated quantity successfully in cart for product " + productID ;
	}
	public List<CartDTO> getCartProducts(String buyerID) throws UserException {
		List<Cart> carts;
		carts = cartRepo.findByBuyerID(buyerID);
		if(carts.isEmpty())
		{
			throw new UserException("Cart is empty");
		}
		List<CartDTO> cartDTOs = new ArrayList<CartDTO>();
		for(Cart cart : carts)
		{
			cartDTOs.add(CartDTO.createCartDTO(cart));
		}
		return cartDTOs;
	}
	public String buyerBecomePrivileged(String buyerID) {
		Buyer buyer = buyerRepo.findById(buyerID).orElse(null);
		String rewardPoints = buyer.getRewardPoints();
		if(buyer.getIsPrivileged().equals("true"))
		{
			return "buyer is already a previleged buyer";
		}
		else if(Integer.parseInt(rewardPoints) >= 10000)
		{
			buyer.setIsPrivileged("true");
			buyerRepo.save(buyer);
			return "buyer became privileged";
		}
		else
		{
			return "buyer need 10000 reward points to become privileged buyer. buyer has "+ rewardPoints;
		}
	}
	public String updateRewardPoints(String buyerID, String rewardPoints) {
		Buyer buyer = buyerRepo.findById(buyerID).orElse(null);
		buyer.setRewardPoints(rewardPoints);
		buyerRepo.save(buyer);
		return "reward points have been updated. Number of Reward points available are "+rewardPoints;
	}
	public int getRewardPoints(String buyerID) {
		Buyer buyer = buyerRepo.findById(buyerID).orElse(null);
		int rp = Integer.parseInt(buyer.getRewardPoints());
		if(buyer.getIsPrivileged().equals("false"))
		{
			return rp*(-1); 
		}
		return rp;
	}

	public String addRewardPoints(String buyerID, String rewardPoints) {
		Buyer buyer = buyerRepo.findById(buyerID).orElse(null);
		String result = String.valueOf(Integer.parseInt(rewardPoints) + Integer.parseInt(buyer.getRewardPoints()));
		buyer.setRewardPoints(result);
		buyerRepo.save(buyer);
		return "reward points have been added. Number of Reward points available are "+result;
	}
	public String deductRewardPoints(String buyerID, String rewardPoints) {
		Buyer buyer = buyerRepo.findById(buyerID).orElse(null);
		if(Integer.parseInt(buyer.getRewardPoints()) < Integer.parseInt(rewardPoints))
		{
			return null;
		}
		String result =  String.valueOf(Integer.parseInt(buyer.getRewardPoints()) - Integer.parseInt(rewardPoints));
		buyer.setRewardPoints(result);
		buyerRepo.save(buyer);
		return "reward points have been deducted. Number of Reward points available are "+result;
	}
	
	public int getQuantityByBuyerIDAndProductID(String buyerID, String productID)
	{
		Cart cart = cartRepo.findById(new CartPK(buyerID, productID)).orElse(null);
		if(cart == null)
		{
			return 0;
		}
		return cart.getQuantity();
	}
}
