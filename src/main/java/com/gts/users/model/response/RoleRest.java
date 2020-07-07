package com.gts.users.model.response;

import java.util.List;

public class RoleRest {

    private long gts_role_id;
	
	private String gts_role_name;

	private List<PermissionRest> permissions;
	
	
	
	
	public List<PermissionRest> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionRest> permissions) {
		this.permissions = permissions;
	}

	public long getGts_role_id() {
		return gts_role_id;
	}

	public void setGts_role_id(long gts_role_id) {
		this.gts_role_id = gts_role_id;
	}

	public String getGts_role_name() {
		return gts_role_name;
	}

	public void setGts_role_name(String gts_role_name) {
		this.gts_role_name = gts_role_name;
	}
	
	
	
}
