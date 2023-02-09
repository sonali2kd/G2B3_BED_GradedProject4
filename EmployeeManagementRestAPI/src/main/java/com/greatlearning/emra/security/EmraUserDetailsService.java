package com.greatlearning.emra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.greatlearning.emra.controller.UserRepository;
import com.greatlearning.emra.security.entity.User;

public class EmraUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User entityObj =  userRepository.getUserByUsername(username);
		
		EmraUserDetails userDetails = new EmraUserDetails(entityObj);
		
		return userDetails;
	}
	
	
	
}
