package com.newlecture.web.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityContextConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*
		<http>
			<intercept-url pattern="/admin/**" 
				access="hasRole('ROLE_ADMIN')"/>
			
			<form-login
				login-page="/member/login"
				default-target-url="/index"
			/>
			
			<logout logout-url="/member/logout"
				logout-success-url="/index" 
			/>
			<!-- <csrf disabled="true"/> -->
			<access-denied-handler error-page="/error/403"/>
		</http>*/
		
		http
			.authorizeRequests()
				.antMatchers("/admin/**")
					.hasRole("ROLE_ADMIN")
				.antMatchers("/student/**")
					.hasAnyRole("ROLE_STUDENT", "ROLE_ADMIN")
				.and()
			.formLogin()
				.loginPage("/member/login")
				.defaultSuccessUrl("/index")
				.and()
			.logout()
				.logoutUrl("/member/logout")
				.logoutSuccessUrl("/index")
			//	.and()
			//.csrf()
			//	.disable()
			;
		
				
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("SELECT ID, PWD PASSWORD, 1 ENABLED FROM MEMBER WHERE ID=?")
				.authoritiesByUsernameQuery("SELECT ID, 'ROLE_ADMIN' AUTHORITY FROM MEMBER WHERE ID=?")
				.passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
