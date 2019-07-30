package com.newlecture.web.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;

public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public UserDetails loadUserByUsername(String username/*email*/) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		// 이건 두째형과 같은 경우의 예
		//Member member = memberDao.getByEmail(username);
		try {
			
			Member member = memberDao.get(username);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return member;
	}

}
