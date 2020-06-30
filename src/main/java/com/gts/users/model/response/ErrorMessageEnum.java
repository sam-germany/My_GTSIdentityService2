package com.gts.users.model.response;

public enum ErrorMessageEnum {

	    MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
	    RECORD_ALREADY_EXISTS("Record already exists"),
	    INTERNAL_SERVER_ERROR("Internal server error"),
	    NO_RECORD_FOUND("Record with provided id is not found"),
	    AUTHENTICATION_FAILED("Authentication failed"),
	    COULD_NOT_UPDATE_RECORD("Could not update record"),
	    COULD_NOT_DELETE_RECORD("Could not delete record"),
	    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified"),
	    USER_IS_NOT_ACTIVATED("User is not activated"),
	    USERNAME_OR_PASSWORD_INVALID("Username or Passowrd is invald ");
	
	 
	private String errorMessageEnum;
	
	
	
	  
	ErrorMessageEnum(String errorMessageEnum) {
		this.errorMessageEnum = errorMessageEnum;
	}




	public String getErrorMessageEnum() {
		return errorMessageEnum;
	}




	public void setErrorMessageEnum(String errorMessageEnum) {
		this.errorMessageEnum = errorMessageEnum;
	}

	
	
	
}
