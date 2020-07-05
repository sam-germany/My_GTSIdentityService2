package com.gts.users.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gts.users.SpringApplicationContext;
import com.gts.users.entities.UserEntity;
import com.gts.users.exceptions.UserServiceException;
import com.gts.users.model.request.UserLoginRequestModel;
import com.gts.users.model.response.ErrorMessageEnum;
import com.gts.users.repositories.UserRepository;
import com.gts.users.services.UserService;
import com.gts.users.shared.dto.UserDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;

	private final UserRepository userRepo;
	
	private Authentication authentication;

	
	public AuthenticationFilter(AuthenticationManager authenticationManager , UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.userRepo = userRepository;
	}
	  
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpReq, 
			                                    HttpServletResponse httpRes) throws AuthenticationException {

		try {
			UserLoginRequestModel creds = new ObjectMapper()
					                                       .readValue(httpReq.getInputStream(), UserLoginRequestModel.class);
		 
	     		try {
	     			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getGts_user_email(),
		    		                                  		                                       creds.getGts_user_password(),
		    		                                   		                                       new ArrayList<>()  ));
		            }catch(BadCredentialsException e) {
	        
			             UserEntity userEntity = userRepo.findByEmail(creds.getGts_user_email());
	            
			             if(userEntity.getGts_user_login_tries() > 5) {
			    	        userEntity.setGts_user_status(false);
     			           }else {
	       		      	       userEntity.setGts_user_login_tries(userEntity.getGts_user_login_tries()+1);
			                      }    
			          userRepo.save(userEntity);
			          
			          throw new UserServiceException(ErrorMessageEnum.AUTHENTICATION_FAILED.getErrorMessageEnum());
	                  } 
			
		        return authentication; 
		}catch (IOException e) {
                 throw new RuntimeException(e);
                      		}
                                                      	 }

	@Override
	protected void successfulAuthentication(HttpServletRequest httpReq,
			                               HttpServletResponse httpRes,
			                                FilterChain chain, 
			                                Authentication authResult) throws IOException, ServletException {
		
		
		String userName = ((UserPrincipal) authResult.getPrincipal())
				                                            .getUsername();
     
		String token = Jwts.builder()
				           .setSubject(userName)
				           .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				           .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
				           .compact();
		
      UserService   userService  = (UserService)SpringApplicationContext.getBean("userServiceImpl");
	  UserDto userDto = userService.getUserByEmail(userName);
		
		
		
		httpRes.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		httpRes.addIntHeader("UserId", (int) userDto.getGts_user_id());
		
		
		
		UserEntity userEntity = userRepo.findByEmail(userDto.getGts_user_email());
		
		   userEntity.setGts_user_login_tries(0);
		   userRepo.save(userEntity);
	}
}
