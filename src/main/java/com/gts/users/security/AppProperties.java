package com.gts.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

	@Autowired
	private Environment env;
	
	public String getTokenSecret(){
		return env.getProperty("tockenSecret");
	}
	
	
	public String getResponse200() {
		return env.getProperty("http.response.200");
	}
	public String getResponse500() {
		return env.getProperty("http.response.500");
	}
	
	
	
	public String getAccountCreatedSuccess() {
		return env.getProperty("http.resp.account.created.success");
	}
	public String getAccountCreatedFailure() {
		return env.getProperty("http.resp.account.created.failure");
	}
	
	
	public String getAcoountUpdationSuccess() {
		return env.getProperty("http.message.account.updated.success");
	}
	public String getAcoountUpdationFailure() {
		return env.getProperty("http.message.account.updated.failure");
	}
	
	public String getSuccessTrue() {
		return env.getProperty("http.message.success.responsetrue");
	}
	
	public String getSuccessFalse() {
		return env.getProperty("http.message.success.responsefalse");
	}
	
	
	
	
}
