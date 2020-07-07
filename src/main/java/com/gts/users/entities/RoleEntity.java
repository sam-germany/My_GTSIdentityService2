package com.gts.users.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="gts_roles")
public class RoleEntity implements Serializable{
	

	private static final long serialVersionUID = 6139246248690938895L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long gts_role_id;
	
	private String gts_role_name;
	
	private boolean gts_role_status;
	
	private String gts_role_description;
	
	
	@ManyToMany(mappedBy = "roles")
	private Collection<UserEntity> users;
	
	@ManyToMany(cascade = CascadeType.PERSIST , fetch = FetchType.EAGER)
    @JoinTable(name="gts_roles_permissions_map" , joinColumns = @JoinColumn(name="gts_role_id", referencedColumnName = "gts_role_id"),
    		                               inverseJoinColumns = @JoinColumn(name="gts_permission_id" , referencedColumnName = "gts_permission_id"))
	private Collection<PermissionEntity> permissions;


	public RoleEntity() { }
	
	
	public RoleEntity(String name, boolean status) {
		this.gts_role_name = name;
		this.gts_role_status= status;
	}

	public Collection<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Collection<UserEntity> users) {
		this.users = users;
	}

	public Collection<PermissionEntity> getPermissions() {
		return permissions;
	}

	public void setPermissions(Collection<PermissionEntity> permissions) {
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

	public boolean isGts_role_status() {
		return gts_role_status;
	}

	public void setGts_role_status(boolean gts_role_status) {
		this.gts_role_status = gts_role_status;
	}

	public String getGts_role_description() {
		return gts_role_description;
	}

	public void setGts_role_description(String gts_role_description) {
		this.gts_role_description = gts_role_description;
	}


}
