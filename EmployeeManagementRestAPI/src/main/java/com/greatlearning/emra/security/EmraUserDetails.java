package com.greatlearning.emra.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.greatlearning.emra.security.entity.User;
import com.greatlearning.emra.security.entity.Role;

public class EmraUserDetails implements UserDetails {

	private User userObj;

	public EmraUserDetails(User userObj) {
		
		this.userObj = userObj;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<SimpleGrantedAuthority> returnResult =
				new ArrayList<>();
			
			List<Role> roles = userObj.getRoles();
			
			for (Role roleObj : roles) {
				
				String rName = roleObj.getName();
				
				SimpleGrantedAuthority sga = 
					new SimpleGrantedAuthority(rName);
				
				returnResult.add(sga);
			}
			
			// TODO Auto-generated method stub
			return returnResult;
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userObj.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userObj.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
