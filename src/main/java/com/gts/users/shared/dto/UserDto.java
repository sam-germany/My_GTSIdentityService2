package com.gts.users.shared.dto;

import java.io.Serializable;

public class UserDto implements Serializable{


	private static final long serialVersionUID = -3132353444802327151L;

	private long gts_user_id;
	private String gts_user_email;
	private String gts_user_password;
	private String gts_user_first_name;
	private String gts_user_last_name;
	private String gts_user_mobile;
    private boolean gts_user_status;
    private int gts_user_login_tries;
    private boolean gts_is_mobile_validated;
    
    
	public long getGts_user_id() {
		return gts_user_id;
	}
	public void setGts_user_id(long gts_user_id) {
		this.gts_user_id = gts_user_id;
	}
	public String getGts_user_email() {
		return gts_user_email;
	}
	public void setGts_user_email(String gts_user_email) {
		this.gts_user_email = gts_user_email;
	}
	public String getGts_user_password() {
		return gts_user_password;
	}
	public void setGts_user_password(String gts_user_password) {
		this.gts_user_password = gts_user_password;
	}
	public String getGts_user_first_name() {
		return gts_user_first_name;
	}
	public void setGts_user_first_name(String gts_user_first_name) {
		this.gts_user_first_name = gts_user_first_name;
	}
	public String getGts_user_last_name() {
		return gts_user_last_name;
	}
	public void setGts_user_last_name(String gts_user_last_name) {
		this.gts_user_last_name = gts_user_last_name;
	}
	public String getGts_user_mobile() {
		return gts_user_mobile;
	}
	public void setGts_user_mobile(String gts_user_mobile) {
		this.gts_user_mobile = gts_user_mobile;
	}
	public boolean isGts_user_status() {
		return gts_user_status;
	}
	public void setGts_user_status(boolean gts_user_status) {
		this.gts_user_status = gts_user_status;
	}
	public int getGts_user_login_tries() {
		return gts_user_login_tries;
	}
	public void setGts_user_login_tries(int gts_user_login_tries) {
		this.gts_user_login_tries = gts_user_login_tries;
	}
	public boolean isGts_is_mobile_validated() {
		return gts_is_mobile_validated;
	}
	public void setGts_is_mobile_validated(boolean gts_is_mobile_validated) {
		this.gts_is_mobile_validated = gts_is_mobile_validated;
	}


}
