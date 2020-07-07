package com.gts.users.shared.dto;

import java.io.Serializable;
import java.util.List;

public class RoleDto implements Serializable{

	private static final long serialVersionUID = -4387976919170303270L;
	
	
	private long gts_role_id;
	private String gts_role_name;
	private UserDto userdetails;
	
	private List<PermissionDto> permissions;
   
	
	
	
	
	public List<PermissionDto> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionDto> permissions) {
		this.permissions = permissions;
	}

	public long getGts_role_id() {
		return gts_role_id;
	}

	public void setGts_role_id(long gts_role_id) {
		this.gts_role_id = gts_role_id;
	}

	public UserDto getUserdetails() {
		return userdetails;
	}

	public void setUserdetails(UserDto userdetails) {
		this.userdetails = userdetails;
	}


	public String getGts_role_name() {
		return gts_role_name;
	}

	public void setGts_role_name(String gts_role_name) {
		this.gts_role_name = gts_role_name;
	}


    

}
