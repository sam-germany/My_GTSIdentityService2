package com.gts.users.repositories;

import com.gts.users.SpringApplicationContext;
import com.gts.users.security.AppProperties;

public class ResponseMessageConstants {

	
	
    public static String getResponse200() {
    	AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
    	       return appProperties.getResponse200();
    }     
    public static String getResponse500() {
    	AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
    	       return appProperties.getResponse500();
    } 
    
    
    
    
    
    
    public static String getSuccessTrue() {
    	AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
    	       return appProperties.getSuccessTrue();
    }
    public static String getSuccessFalse() {
    	AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
    	       return appProperties.getSuccessFalse();
    }

    public static String getAccountCreatedSuccess() {
    	AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
    	       return appProperties.getAccountCreatedSuccess();
    }
    public static String getAccountCreatedFailure() {
    	AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
    	       return appProperties.getAccountCreatedFailure();
    }
    public static String getAcoountUpdationSuccess() {
    	AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
    	       return appProperties.getAcoountUpdationSuccess();
    }
    
    public static String getAcoountUpdationFailure() {
    	AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
    	       return appProperties.getAcoountUpdationFailure();
    }
    
}
