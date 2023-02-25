package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/*This is deprecated from spring 5.7 spring security
@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers("/users/public")
		.permitAll().and()
		.formLogin();
		
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser("rahul").password(this.passwordEncoder().encode("rahul")).roles("Admin");
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
	
}*/

@Configuration
@EnableWebSecurity
public class MySecurityConfig{
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails normalUser = User.withUsername("rahul").password(passwordEncoder().encode("rahul")).roles("NORMAL").build();
		UserDetails adminUser = User.withUsername("chayan").password(passwordEncoder().encode("chayan")).roles("ADMIN").build();
		
		
		return new InMemoryUserDetailsManager(normalUser,adminUser);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf().disable()
		.authorizeHttpRequests().requestMatchers("/users")
				/*
				 * .requestMatchers("/users/admin") .hasRole("ADMIN")
				 * .requestMatchers("/users/normal") .hasRole("NORMAL")
				 * .requestMatchers("/users/public")
				 */
		.permitAll()
		.anyRequest().authenticated().and()
		.formLogin();
		
		return httpSecurity.build();
	}
	
}
