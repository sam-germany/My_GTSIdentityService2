package com.gts.users.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gts.users.entities.PermissionEntity;
import com.gts.users.entities.RoleEntity;
import com.gts.users.entities.UserEntity;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1879585839504604929L;

	UserEntity userEntity;
	private long userId;
	
	public UserPrincipal(UserEntity userEntity) {
		this.userEntity = userEntity;
		this.userId = userEntity.getGts_user_id();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> grantedAll = new ArrayList<>();
		
 		Collection<RoleEntity> roles1 = userEntity.getRoles();
 		List<PermissionEntity> permissionEntities = new ArrayList<>();
 		
		if( roles1 == null) return grantedAll;
 		
		roles1.forEach((role2) -> {
      			      grantedAll.add(new SimpleGrantedAuthority(role2.getGts_role_name()));
			          permissionEntities.addAll(role2.getPermissions());
	 	                 });
		
		permissionEntities.forEach((permission) ->{
			           grantedAll.add(new SimpleGrantedAuthority(permission.getGts_permission_name()));
		                 });
 		
		return grantedAll;
	}

	@Override
	public String getPassword() {
		
		
		return this.userEntity.getGts_user_password();
	}

	@Override
	public String getUsername() {
		return this.userEntity.getGts_user_email();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	
	
	
}
