package com.newlecture.web.dao.mybatis;

import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;

@Repository
public class MybatisMemberDao implements MemberDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Member get(String id) throws ClassNotFoundException, SQLException {

		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);	
		return memberDao.get(id);
	}

	@Override
	public int insert(Member member) throws ClassNotFoundException, SQLException {
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);	
		return memberDao.insert(member);
	}

}
