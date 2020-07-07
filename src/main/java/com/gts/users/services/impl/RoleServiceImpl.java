package com.gts.users.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gts.users.entities.PermissionEntity;
import com.gts.users.entities.RoleEntity;
import com.gts.users.entities.UserEntity;
import com.gts.users.repositories.RoleRepository;
import com.gts.users.repositories.UserRepository;
import com.gts.users.services.RoleService;
import com.gts.users.shared.dto.RoleDto;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	
	@Override
	public List<RoleDto> getAllRolesUserId(long gts_user_id) {

		  List<RoleDto> returnValue = new ArrayList<>();
		  ModelMapper modelMapper  = new ModelMapper();
		  
		  UserEntity  userEntity  =  userRepo.findByUserId(gts_user_id);
		  
		  Collection<RoleEntity> roles1 =	userEntity.getRoles();
		  
		  if(userEntity == null) return returnValue;
		 
		
	      roles1.forEach((role2) ->{
	    	                    returnValue.add(modelMapper.map(role2, RoleDto.class)) ;
	                     });
	      
	      
		return returnValue;
	}
}
