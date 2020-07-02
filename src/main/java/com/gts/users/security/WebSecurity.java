package com.gts.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.gts.users.repositories.UserRepository;
import com.gts.users.services.UserService;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
@EnableOAuth2Client
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	
	   private final UserService userDetailsService;
	   private final BCryptPasswordEncoder bCryptPasswordEncoder;
	   private final UserRepository userRepo;	
   
	   
	    @Autowired
		@Qualifier("oauth2authSuccessHandler")
		private AuthenticationSuccessHandler oauth2authSuccessHandler;
	   
	   public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder , UserRepository userRepo) {
		   this.userDetailsService = userDetailsService;
		   this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		   this.userRepo = userRepo;
	}



	@Override
	    protected void configure(HttpSecurity http) throws Exception {
		
	             http.csrf().disable().authorizeRequests()
	                 .antMatchers("/login").permitAll()
	                 .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
	                 .antMatchers(HttpMethod.POST, SecurityConstants.CREATE_USER).permitAll()
	                 .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
          //           .antMatchers("/users/job_seeker").hasAuthority("CAN_VIEW_JOB_SEEKER_PROFILE")
	                 .anyRequest().authenticated()
	                 .and()
	                 .addFilter(getAuthenticationFilter())
	                 .addFilter(new AuthorizationFilter(authenticationManager(), userRepo))
	                 .sessionManagement()
	                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)// if required then we can change the
	                                                                     // policy from STATELESS to any other also
	                 .and().oauth2Login().loginPage("/login").successHandler(oauth2authSuccessHandler);
	              }
	   
	   
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
           auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        }
        
        public AuthenticationFilter getAuthenticationFilter() throws Exception {
			final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager(),userRepo);
			filter.setFilterProcessesUrl("/users/login");
			return filter;
		}
}
