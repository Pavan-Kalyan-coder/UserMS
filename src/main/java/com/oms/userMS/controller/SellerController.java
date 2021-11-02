package com.oms.userMS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oms.userMS.dto.LoginDTO;
import com.oms.userMS.dto.SellerDTO;
import com.oms.userMS.exception.UserException;
import com.oms.userMS.service.UserService;

@RestController
@CrossOrigin
public class SellerController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	
	@Autowired
	Environment environment;
	
	//Create New Seller
	@PostMapping(value = "/seller/register",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createSeller(@RequestBody SellerDTO sellerDTO) {
		logger.info("Creation request for seller {}", sellerDTO);
		String result;
		try {
			result = userService.createSeller(sellerDTO);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (UserException e) {
			String s = environment.getProperty(e.getMessage());
			return new ResponseEntity<>(s,HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	// Login Seller
	@PostMapping(value = "/seller/login",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> sellerLogin(@RequestBody LoginDTO loginDTO) {
		logger.info("Login request for seller {} with password {}", loginDTO.getEmailID(),loginDTO.getPassword());
		String result;
		try {
			result = userService.sellerLogin(loginDTO);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (UserException e) {
			String s = environment.getProperty(e.getMessage());
			return new ResponseEntity<>(s,HttpStatus.NOT_FOUND);
		}
		
	}
	
	//Buyer inactivating account
	@PutMapping(value = "/seller/inactivate/{buyerID}")
	public ResponseEntity<String> inactivateBuyer(@PathVariable String sellerID)
	{
		logger.info("Making Seller {} Inactive", sellerID);
		String result;
		try 
		{
			result = userService.inactivateSeller(sellerID);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (UserException e) {
			String s = environment.getProperty(e.getMessage());
			return new ResponseEntity<>(s,HttpStatus.EXPECTATION_FAILED);
		}
			
	}
		
	//Buyer deleting account
	@DeleteMapping(value = "/seller/delete/{sellerID}")
	public ResponseEntity<String> deleteBuyer(@PathVariable String sellerID)
	{
		logger.info("Deleting Seller {}", sellerID);
		String result;
		try 
		{
			result = userService.deleteSeller(sellerID);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (UserException e) {
			String s = environment.getProperty(e.getMessage());
			return new ResponseEntity<>(s,HttpStatus.EXPECTATION_FAILED);
		}
			
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
}
