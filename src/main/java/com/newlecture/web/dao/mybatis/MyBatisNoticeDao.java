package com.newlecture.web.dao.mybatis;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

@Repository
//@Repository("mybatisNoticeDao")
public class MyBatisNoticeDao implements NoticeDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int getCount() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<NoticeView> getList() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return getList(null, "title", "");
	}

	@Override
	public List<NoticeView> getList(Integer page) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return getList(page, "title", "");
	}

	@Override
	public List<NoticeView> getList(Integer page, String field, String query) throws ClassNotFoundException, SQLException {
		
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);	
				
		return noticeDao.getList(page, field, query);		
	}

	@Override
	public Notice get(int id) throws ClassNotFoundException, SQLException {
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);	
		
		return noticeDao.get(id);
	}

	@Override
	public Notice getPrev(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice getNext(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		
		return noticeDao.insert(notice);
	}

	@Override
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		
		return noticeDao.update(notice);
	}

	@Override
	public int delete(int id) throws ClassNotFoundException, SQLException {
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		
		return noticeDao.delete(id);
	}

	@Override
	public int getLastId() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
