package com.oms.userMS.dto;

public class LoginDTO {
	String emailID;
	String password;
	
	
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginDTO [emailID=" + emailID + ", password=" + password + "]";
	}
	public LoginDTO() {
		super();
	}
}
