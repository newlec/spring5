package com.newlecture.web.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails /*로그인 정보+권한정보 + Member 데이터*/
						extends Member 
						implements UserDetails {

	public MyUserDetails() {
		// TODO Auto-generated constructor stub
	}
	
	
	public MyUserDetails(Member member) {
		setId(member.getId());
		setName(member.getName());
		setPwd(member.getPwd());
		setAge(member.getAge());
		setRegdate(member.getRegdate());		
		
		System.out.println(getId() + ", " + getPwd());
	}


	// 권한 정보 ================
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		//"ROLE_ADMIN","ROLE_STUDENT"
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
		
		return authorities;
	}

	// 인증정보 =================
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return getPwd();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getId();
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
