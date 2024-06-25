package com.sppm.GymManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import com.sppm.GymManagementSystem.service.GymUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private GymUserService service;
	@Autowired
	private Encoderconfig econfig;

	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder authority) throws Exception {
		authority.userDetailsService(service).passwordEncoder(econfig.passwordEncoder());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/register").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/loginpage").failureUrl("/loginerror").loginProcessingUrl("/login").permitAll().and().logout().logoutSuccessUrl("/index");
		http.csrf().disable();
	}
}
