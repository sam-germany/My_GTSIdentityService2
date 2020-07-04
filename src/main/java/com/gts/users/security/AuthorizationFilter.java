package com.gts.users.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.gts.users.entities.UserEntity;
import com.gts.users.repositories.UserRepository;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	private final UserRepository userRepo;                                    
	
	public AuthorizationFilter(AuthenticationManager authenticationManager , UserRepository userRepository) {
		super(authenticationManager);
		this.userRepo = userRepository;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpReq,
			                              HttpServletResponse httpRes, 
			                                       FilterChain chain)  throws IOException, ServletException {

		String header = httpReq.getHeader(SecurityConstants.HEADER_STRING);

		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(httpReq, httpRes);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication22(httpReq);
		SecurityContextHolder.getContext().setAuthentication(authentication);    // SecurityContextHolder is a object which will
		chain.doFilter(httpReq, httpRes);                                        // be shared in whole project.

	}

	
	private UsernamePasswordAuthenticationToken getAuthentication22(HttpServletRequest request) {

		String token = request.getHeader(SecurityConstants.HEADER_STRING);

		if (token != null) {
			token = token.replace(SecurityConstants.TOKEN_PREFIX, "");

			String user = Jwts.parser()
					          .setSigningKey(SecurityConstants.getTokenSecret())
					          .parseClaimsJws(token)
					          .getBody()
					          .getSubject();

			if (user != null) {
				UserEntity userEntity = userRepo.findByEmail(user);
				if(userEntity == null) return null;
				
   			    UserPrincipal userPrincipal = new UserPrincipal(userEntity);

   			 UsernamePasswordAuthenticationToken token2  = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
   			 
   			 System.out.println(token2);    
   			   return token2; 
   			    
		//		return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
				
			}                                
			                                  
                                             
			return null;               

		}
		return null;

	}

}
