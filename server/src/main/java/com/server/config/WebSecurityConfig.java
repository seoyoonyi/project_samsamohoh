package com.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

import com.server.securityconfig.JwtAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	
	protected void configure(HttpSecurity http)throws Exception{
		http.cors()
			.and()
			.csrf()
			.disable()
			.httpBasic()
			.disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			//.and()
			//.authorizeRequests()
			//.antMatchers("/","/auth/**","/swagger-ui.html","/api/v2/**","/swagger/**","/swagger-resources/**","/v2/api-docs","/webjars/**").permitAll();
			//.anyRequest().authenticated();
		
		http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);
	}
	
}
