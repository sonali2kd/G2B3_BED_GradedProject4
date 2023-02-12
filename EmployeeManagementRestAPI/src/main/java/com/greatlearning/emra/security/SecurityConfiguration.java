package com.greatlearning.emra.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{


	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
		.withUser("sonali").password("sonali").roles("ADMIN")
		.and()
		.withUser("sapna").password("sapna").roles("USER");

		PasswordEncoder encode = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		
	}
	
	@Bean 
	PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();

	}

	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/","/employees/list").hasAnyRole("USER","ADMIN")
		.antMatchers("/","/employees/list","/employees/showFormForAdd","/employees","/employees/showFormForUpdate","/employees/delete")
		.hasRole("ADMIN").antMatchers("/").permitAll().and().formLogin()
		.loginProcessingUrl("/login")
		.successForwardUrl("/employees/list").permitAll().and().logout().logoutSuccessUrl("/login").permitAll()
		.and().exceptionHandling().accessDeniedPage("/employees/403");

	}
	
}
