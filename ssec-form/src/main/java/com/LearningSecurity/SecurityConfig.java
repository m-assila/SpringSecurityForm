package com.LearningSecurity;

import org.aspectj.weaver.ast.And;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final PasswordEncoder pwEncoder=PasswordEncoderFactories.createDelegatingPasswordEncoder();
	// authentication
	@Bean
	UserDetailsService authentication() {
		UserDetails simo=User.builder()
				.username("simo")
				.password(pwEncoder.encode("pass1"))
				.roles("USER")
				.build();
		UserDetails stef=User.builder()
				.username("stef")
				.password(pwEncoder.encode("pass2"))
				.roles("USER","ADMIN")
				.build();
		
		System.out.println("simo's password is : "+simo.getPassword());
		System.out.println("stef's password is : "+stef.getPassword());
		return new InMemoryUserDetailsManager(simo,stef);
		
	}
	
	

	@Override // authorization
	protected void configure(HttpSecurity http) throws Exception {
		http 
		.authorizeRequests().mvcMatchers("/admin/**").hasRole("ADMIN")
		.and()
		.formLogin()
		.and()
		.httpBasic();
	}
	
	

}
