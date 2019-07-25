package com.newlecture.web.dao;

import java.sql.SQLException;

import com.newlecture.web.entity.Member;

public interface MemberDao {

	Member get(String id) throws ClassNotFoundException, SQLException;
	int insert(Member member) throws ClassNotFoundException, SQLException;
	
}
