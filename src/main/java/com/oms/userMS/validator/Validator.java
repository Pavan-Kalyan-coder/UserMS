package com.oms.userMS.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.oms.userMS.dto.BuyerDTO;
import com.oms.userMS.dto.SellerDTO;
import com.oms.userMS.exception.UserException;
import com.oms.userMS.service.UserService;

public class Validator {
	
	@Autowired
	UserService userService;
	
	public static void validateBuyer(BuyerDTO buyer) throws UserException {
		
		if(validateName(buyer.getName()) == false)
			throw new UserException("ENTER VALID NAME");
		
		if(validateEmail(buyer.getEmailID()) == false)
			throw new UserException("ENTER VALID EMAIL");
		
		if(validateContactNumber(buyer.getPhoneNo()) == false)
			throw new UserException("ENTER VALID NUMBER");
		
			
		if(validatePassword(buyer.getPassword()) == false)
			throw new UserException("Enter VALID PASSWORD");
		
	}
	
	public static void validateSeller(SellerDTO buyer) throws UserException {
		
		if(validateName(buyer.getName()) == false)
			throw new UserException("ENTER VALID NAME");
		
		if(validateEmail(buyer.getEmailID()) == false)
			throw new UserException("ENTER VALID EMAIL");
		
		if(validateContactNumber(buyer.getPhoneNo()) == false)
			throw new UserException("ENTER VALID NUMBER");
		
			
		if(validatePassword(buyer.getPassword()) == false)
			throw new UserException("Enter VALID PASSWORD");
		
	}
	
	
	public static boolean validateName(String name)
	{
		
		String regex = "[A-Za-z]+([ ][A-Za-z]+)*";
		
		if(name.matches(regex))
			return true;
		
		return false;
		
	}
	
	public static boolean validateEmail(String email)
	{
		String regex = "[A-za-z1-9]+@[A-za-z]+[\\.]com";
		
		if(email.matches(regex))
			return true;
		
		return false;
	}
	
	public static boolean validateContactNumber(String contactNumber)
	{
		
		String regex = "[6,7,8,9][0-9]{9}";
		
		if(contactNumber.matches(regex))
			return true;
		
		return false;
	}
	
	public static boolean validatePassword(String password)
	{
		String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{7,20}$";
		
		if(password.matches(regex))
			return true;
		
		return false;
	}	

}