package com.newlecture.web.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.MyUserDetails;

public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberDao memberDao;
	
	//@Autowired
	//private MemberRoleDao memberRoleDao;
	
	@Override
	public UserDetails loadUserByUsername(String username/*email*/) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		// 이건 두째형과 같은 경우의 예
		//Member member = memberDao.getByEmail(username);
		
		MyUserDetails userDetails = null;
		try {
			
			Member member = memberDao.get(username);
			//List<MemberRole> mroles = memberRoleDao.getByMemberId(username);
			// MemberRole - memeberId, roleId
			//memberId | roleId
			 // dragon     ROLE_STUDENT			   
			
			// Member 데이터(**), 인증과 권한 정보
			userDetails = new MyUserDetails(member);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userDetails;
	}
	
	

}
