package com.gts.users.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gts.users.entities.UserEntity;
import com.gts.users.exceptions.UserServiceException;
import com.gts.users.model.response.ErrorMessageEnum;
import com.gts.users.repositories.UserRepository;
import com.gts.users.security.UserPrincipal;
import com.gts.users.services.UserService;
import com.gts.users.shared.dto.UserDto;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	BCryptPasswordEncoder  bCryptPasswordEncoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		  UserEntity userEntity = userRepo.findByEmail(username);
		  if(userEntity == null) 
			     throw new UserServiceException(ErrorMessageEnum.NO_RECORD_FOUND.getErrorMessageEnum());
		  
		  if(!userEntity.isGts_user_status()) return null;
		  
		  
	   return  new UserPrincipal(userEntity);
		  
	}
	

	@Override
	public UserDto getUserByEmail(String email) {

		UserEntity userEntity = userRepo.findByEmail(email);
		
		if(userEntity == null) 
			    throw new UserServiceException(ErrorMessageEnum.NO_RECORD_FOUND.getErrorMessageEnum());
		
	     UserDto returnValue = new ModelMapper().map(userEntity, UserDto.class);
		
		return returnValue;
	}
	
	
	
	@Override
	public UserDto createUser(UserDto userDto) {

		if(userRepo.findByEmail(userDto.getGts_user_email())  != null)   
			                                                        return null;

		 ModelMapper modelMapper = new ModelMapper();
		 UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
       	  
      	    userEntity.setGts_user_password(bCryptPasswordEncoder.encode(userDto.getGts_user_password()));

  	     	UserEntity  storedUserDetails = userRepo.save(userEntity);

    		UserDto  returnValue = modelMapper.map(storedUserDetails, UserDto.class);

	return returnValue;
	}

 
	   @Override
	    public UserDto getUserByid(long gts_user_id) {
             UserDto returnValue = new UserDto();
             
             UserEntity userEntity = userRepo.findByUserId(gts_user_id);
             
             if( userEntity == null) return  null;
             
             ModelMapper modelMapper = new ModelMapper();
             returnValue = modelMapper.map(userEntity, UserDto.class);
             
		   return returnValue;
	   }


	    @Override
	    public UserDto updateUser(long gts_user_id, UserDto userDto) {

	    	UserDto returnValue = new UserDto();
	    	UserEntity userEntity  = userRepo.findByUserId(gts_user_id);
	    	
	    	if(userEntity == null)   return null;
	    		         
	    	
	    	userEntity.setGts_user_first_name(userDto.getGts_user_first_name());
	    	userEntity.setGts_user_password(bCryptPasswordEncoder.encode(userDto.getGts_user_password()));
	    	
	    	UserEntity updatedUser = userRepo.save(userEntity);
	    	
	    	ModelMapper modelMapper = new ModelMapper();
	    	returnValue = modelMapper.map(updatedUser, UserDto.class);
		
		return returnValue;
	}


		@Override
		public UserDto deleteUser(long gts_user_id) {
			
			UserDto returnValue = new UserDto();
			  UserEntity userEntity = userRepo.findByUserId(gts_user_id);
			  
			  if(userEntity == null)   return null;
			 
				         
			  if(userEntity.isGts_user_status() == false) return null;
			 
			  userEntity.setGts_user_status(false);
			  
			  UserEntity updatedUser = userRepo.save(userEntity);
		    	
		    	ModelMapper modelMapper = new ModelMapper();
		    	returnValue = modelMapper.map(updatedUser, UserDto.class);
		    	
			  return returnValue;
		}


		@Override
		public List<UserDto> getAllUsers(int page, int limit) {

			 List<UserDto> returnValue = new ArrayList<>();
			 ModelMapper modelMapper  = new ModelMapper();
			 
			 if(page > 0) page -=1;      
                                     
                         
			 Pageable pageableRequest   = PageRequest.of(page, limit);
			
			 Page<UserEntity> usersPage = userRepo.findAll(pageableRequest);
			 
			 if(usersPage == null) return null;
			 
			 List<UserEntity> users = usersPage.getContent();
			
			 
			 for(UserEntity userEntity : users) {
                 returnValue.add(modelMapper.map(userEntity, UserDto.class));
			 }
			 
			
			return returnValue;
		}





}
