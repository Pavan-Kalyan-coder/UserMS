package com.oms.userMS.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.oms.userMS.dto.BuyerDTO;
import com.oms.userMS.dto.CartDTO;
import com.oms.userMS.dto.LoginDTO;
import com.oms.userMS.dto.ProductDTO;
import com.oms.userMS.exception.UserException;
import com.oms.userMS.service.UserService;

@RestController
@CrossOrigin
public class BuyerController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	
	@Autowired
	Environment environment;
	
	@Value("${product.uri}")
	String prodUri;

	
	//Buyer Register
	@PostMapping(value = "/buyer/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createBuyer(@RequestBody BuyerDTO buyerDTO) {
		logger.info("Creation request for buyer {}", buyerDTO);
		String result;
		try {
			logger.info("Creation request for buyer {}", buyerDTO);
			result = userService.createBuyer(buyerDTO);
			logger.info("Creation request for buyer {}", buyerDTO);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (UserException e) {
			String s = e.getMessage();
			return new ResponseEntity<>(s,HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	// Login Buyer
	@PostMapping(value = "/buyer/login",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> buyerLogin(@RequestBody LoginDTO loginDTO) {
		logger.info("Login request for buyer {} with password {}", loginDTO.getEmailID(),loginDTO.getPassword());
		String result;
		try {
			result = userService.buyerLogin(loginDTO);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (UserException e) {
			String s = e.getMessage();
			return new ResponseEntity<>(s,HttpStatus.NOT_FOUND);
		}
	
	}
	
	//Buyer inactivating account
	@PutMapping(value = "/buyer/inactivate/{buyerID}")
	public ResponseEntity<String> inactivateBuyer(@PathVariable String buyerID)
	{
		logger.info("Making Buyer {} Inactive", buyerID);
		String result;
		try {
			result = userService.inactivateBuyer(buyerID);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (UserException e) {
			String s = e.getMessage();
			return new ResponseEntity<>(s,HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	//Buyer deleting account
	@DeleteMapping(value = "/buyer/delete/{buyerID}")
	public ResponseEntity<String> deleteBuyer(@PathVariable String buyerID)
	{
		logger.info("Deleting Buyer {}", buyerID);
		String result;
		try {
			result = userService.deleteBuyer(buyerID);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (UserException e) {
			String s = e.getMessage();
			return new ResponseEntity<>(s,HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	// Buyer add Product to Wishlist
	@PostMapping(value = "/buyer/wishlist/add/{buyerID}/{productID}")
	public ResponseEntity<String> addProductToWishlist(@PathVariable String buyerID, @PathVariable String productID) throws Exception
	{
		try 
		{
			ProductDTO productDTO = getProductFromProductMSByProductID(productID);
			String msg = userService.addToWishList(buyerID, productDTO.getProductID());
			return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			String msg = handleException(e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,msg,e);
		}
	}
	
	// Buyer remove Product from Wishlist
	@DeleteMapping(value = "/buyer/wishlist/remove/{buyerID}/{productID}")
	public ResponseEntity<String> removeProductFromWishlist(@PathVariable String buyerID, @PathVariable String productID) throws Exception
	{
		try 
		{
			ProductDTO productDTO = getProductFromProductMSByProductID(productID);
			String msg = userService.removeFromWishList(buyerID, productDTO.getProductID());
			return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			String msg = handleException(e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,msg,e);
		}
	}
	
	// buyer move product from wishlist to cart
	@PostMapping(value = "/buyer/wishlisttocart/move/{buyerID}/{productID}/{quantity}")
	public ResponseEntity<String> moveProductToCart(@PathVariable String buyerID, @PathVariable String productID, @PathVariable int quantity) throws Exception
	{
		if(checkStock(productID, quantity) == false)
		{
			String result = "Requested quantity is not available in stock.";
			return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
		}
		try 
		{
			ProductDTO productDTO = getProductFromProductMSByProductID(productID);
			String result1 = userService.removeFromWishList(buyerID, productDTO.getProductID());
			String result2 = userService.addToCart(buyerID, productDTO.getProductID(), quantity);
			String result = result1+" "+result2;
			return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			String msg = handleException(e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,msg,e);
		}
	}
	
	
	// Buyer add product to cart
	@PostMapping(value = "/buyer/cart/add/{buyerID}/{productID}/{quantity}")
	public ResponseEntity<String> addProductToCart(@PathVariable String buyerID, @PathVariable String productID, @PathVariable int quantity) throws Exception
	{
		try 
		{
			ProductDTO productDTO = getProductFromProductMSByProductID(productID);
			String msg = userService.addToCart(buyerID, productDTO.getProductID(), quantity);
			return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			String msg = handleException(e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,msg,e);
		}
	}
	
	// Buyer remove product from cart
	@DeleteMapping(value = "/buyer/cart/remove/{buyerID}/{productID}")
	public ResponseEntity<String> removeProductFromCart(@PathVariable String buyerID, @PathVariable String productID) throws Exception
	{
//		logger.info("strat");
		try 
		{
//			logger.info("strat");
			ProductDTO productDTO = getProductFromProductMSByProductID(productID);
//			logger.info("end");
			String msg = userService.removeFromCart(buyerID, productDTO.getProductID());
			logger.info(msg);
			return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			String msg = handleException(e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,msg,e);
		}
	}
	// Buyer update product quantity in cart
	@PostMapping(value = "/buyer/cart/updatequantity/{buyerID}/{productID}/{quantity}")
	public ResponseEntity<String> updateProductQuantityInCart(@PathVariable String buyerID, @PathVariable String productID,@PathVariable int quantity) throws Exception
	{
		try 
		{
			ProductDTO productDTO = getProductFromProductMSByProductID(productID);
			String msg = userService.updateQuantityCart(buyerID, productDTO.getProductID(), quantity);
			return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			String msg = handleException(e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,msg,e);
		}
	}
	
	// Buyer fetch all products in cart
	@GetMapping(value = "/buyer/cart/getallproducts/{buyerID}")
	public ResponseEntity<List<CartDTO>> updateProductQuantityInCart(@PathVariable String buyerID) throws Exception
	{
		try 
		{
			List<CartDTO> list = userService.getCartProducts(buyerID);
			return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			String msg = e.getMessage();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg, e);
		}
	}
	@GetMapping(value = "/buyer/getrewardpoints/{buyerID}")
	public ResponseEntity<Integer> getBuyerRewardPoints(@PathVariable String buyerID) throws Exception
	{
		try 
		{
			int points = userService.getRewardPoints(buyerID);
			return new ResponseEntity<>(points,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			String msg = e.getMessage();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg, e);
		}
	}
	
	// buyer become privileged buyer
	@PutMapping(value = "/buyer/{buyerID}/privileged")
	public ResponseEntity<String> buyerBecomePrivileged(@PathVariable String buyerID) {
		logger.info("buyer {} request for becoming privileged buyer", buyerID);
		String result = userService.buyerBecomePrivileged(buyerID);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	// update reward points
	@PutMapping(value = "/buyer/updaterewardpoints/{buyerID}/{rewardPoints}")
	public ResponseEntity<String> buyerAccumulateRewardPoints(@PathVariable String buyerID, @PathVariable String rewardPoints) {
		logger.info("buyer {} request for updating reward points", buyerID);
		String result = userService.updateRewardPoints(buyerID, rewardPoints);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	
	
	
	
	
	public String handleException(Exception e)
	{
		System.out.println(e);
		String msg = "There was some error";
		if(e.getMessage().equals("404 null"))
		{
			msg = "There are no Products for the given product ID";
		}
		return msg;
	}
	public ProductDTO getProductFromProductMSByProductID(String productID)
	{
		ProductDTO productDTO = new RestTemplate().getForObject(prodUri+"/products/productbyid/"+productID, ProductDTO.class);
//		logger.info("strat");
		return productDTO;
	}
	
	
	public boolean checkStock(String productID, int quantity)
	{
		ProductDTO productDTO = getProductFromProductMSByProductID(productID);
		if(productDTO.getStock() <= quantity)
		{
			return false;
		}
		return true;
	}
}
