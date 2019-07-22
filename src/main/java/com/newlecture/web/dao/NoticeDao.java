package com.newlecture.web.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

public interface NoticeDao {	
	
	int getCount() throws ClassNotFoundException, SQLException;
	int getCount(String field, String query) throws ClassNotFoundException, SQLException;
	
	List<NoticeView> getList() throws ClassNotFoundException, SQLException;
	List<NoticeView> getList(Integer page) throws ClassNotFoundException, SQLException;
	
	
	//@Select("SELECT * FROM NOTICE_VIEW\r\n" + 
	//		"		WHERE ${field} LIKE '%${query}%' \r\n" + 
	//		"			AND NUM BETWEEN 1+(#{page}-1)*10 AND #{page}*10")
	List<NoticeView> getList(@Param("page") Integer page
			, @Param("field") String field
			, @Param("query") String query) throws ClassNotFoundException, SQLException;
	
	//@Select("select * from NOTICE where id=#{id}")
	Notice get(int id) throws ClassNotFoundException, SQLException;
	Notice getPrev(int id) throws ClassNotFoundException, SQLException;
	Notice getNext(int id) throws ClassNotFoundException, SQLException;
	
	int insert(Notice notice) throws ClassNotFoundException, SQLException;
	int update(Notice notice) throws ClassNotFoundException, SQLException;
	int delete(int id) throws ClassNotFoundException, SQLException;
	int getLastId() throws ClassNotFoundException, SQLException;
}
