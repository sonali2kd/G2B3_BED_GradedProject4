package com.greatlearning.emra.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
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
		return new BCryptPasswordEncoder();
	}

	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers("/h2-console/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.requestMatchers("/employees/list").hasAnyRole("USER","ADMIN")
		.requestMatchers("/employees/showFormForAdd","/employees/save","/employees/showFormForUpdate","/employees/delete")
		.hasRole("ADMIN").requestMatchers("/").permitAll()
		.and().formLogin();

	}
	
}
