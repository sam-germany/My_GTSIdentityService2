package com.gts.users.services;

import java.util.List;

import com.gts.users.shared.dto.RoleDto;

public interface RoleService {

    List<RoleDto> getAllRolesUserId(long gts_user_id);
	
}
