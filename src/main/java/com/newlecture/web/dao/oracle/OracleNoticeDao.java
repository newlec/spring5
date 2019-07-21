package com.newlecture.web.dao.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

//@Repository
public class OracleNoticeDao implements NoticeDao {
	
	@Override
	public List<NoticeView> getList() throws ClassNotFoundException, SQLException {
		return getList(1, "title","");
	}
	
	@Override
	public List<NoticeView> getList(int page) throws ClassNotFoundException, SQLException {
		return getList(page, "title","");
	}
	
	@Override
	public List<NoticeView> getList(int page, String field, String query) throws ClassNotFoundException, SQLException {
		
		List<NoticeView> list = new ArrayList<>();
		
		int start = 1+(page-1)*10;  // 1, 11, 21, 31, 41,...
		int end = page*10;   // 10, 20, 30, 40, 50
		
		String sql = "SELECT * FROM NOTICE_VIEW " + 
				"WHERE "+field+" LIKE ? AND NUM BETWEEN ? AND ?";
		
		String url = "jdbc:oracle:thin:@192.168.0.15:1521/xepdb1";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "\"newlec\"", "l4class");
		
		//con.setAutoCommit(false);
		
		PreparedStatement st  = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%"); 
		st.setInt(2,  start);
		st.setInt(3,  end);		
		
		/*
		 * String sql2 = "INSERT ... "; PreparedStatement st2 =
		 * con.prepareStatement(sql); st2.setString(1, "%"+query+"%"); st2.setInt(2,
		 * start); st2.setInt(3, end);
		 */
		
		//con.setAutoCommit(false);
		
		ResultSet rs = st.executeQuery();
		//st2.executeUpdate();
		
		//con.commit();
		
		while(rs.next()) {
			NoticeView notice = new NoticeView(
							rs.getInt("id"),
							rs.getString("title"),
							"",
							rs.getString("writer_id"),
							rs.getDate("regdate"),
							rs.getInt("hit"),
							rs.getInt("comment_count")
						);
			
			list.add(notice);			
		}	
		
		//con.commit();
		
		rs.close();
		st.close();
		con.close();	
		
		return list;
	}

	@Override
	public Notice get(int id) throws ClassNotFoundException, SQLException {		
		Notice notice = null;
		
		String sql = "select * from NOTICE where id="+id;
		
		String url = "jdbc:oracle:thin:@192.168.0.15:1521/xepdb1";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "\"newlec\"", "l4class");
		Statement st  = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		if(rs.next()) {
			notice = new Notice(
							rs.getInt("id"),
							rs.getString("title"),
							rs.getString("content"),
							rs.getString("writer_id"),
							rs.getDate("regdate"),
							rs.getInt("hit")
						);	
			
		}	
		
		rs.close();
		st.close();
		con.close();	
		
		return notice;
	}

	@Override
	public Notice getPrev(int id)  throws ClassNotFoundException, SQLException{
		Notice notice = null;
		
		String sql = "SELECT * FROM (SELECT * FROM NOTICE_VIEW ORDER BY REGDATE DESC) " + 
				"WHERE REGDATE < (SELECT REGDATE FROM NOTICE WHERE ID = "+id+") " + 
				"AND ROWNUM = 1";
		
		String url = "jdbc:oracle:thin:@192.168.0.15:1521/xepdb1";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "\"newlec\"", "l4class");
		Statement st  = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		if(rs.next()) {
			notice = new Notice(
					rs.getInt("id"),
					rs.getString("title"),
					"",
					rs.getString("writer_id"),
					rs.getDate("regdate"),
					rs.getInt("hit")
				);			
		}
		
		rs.close();
		st.close();
		con.close();	
		
		return notice;
	}

	@Override
	public Notice getNext(int id)  throws ClassNotFoundException, SQLException{
		
		Notice notice = null;
		
		String sql = "SELECT * FROM (SELECT * FROM NOTICE_VIEW ORDER BY REGDATE) " + 
				"WHERE REGDATE > (SELECT REGDATE FROM NOTICE WHERE ID = "+id+") " + 
				"AND ROWNUM = 1";
		
		String url = "jdbc:oracle:thin:@192.168.0.15:1521/xepdb1";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "\"newlec\"", "l4class");
		Statement st  = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		if(rs.next()) {
			notice = new Notice(
					rs.getInt("id"),
					rs.getString("title"),
					"",
					rs.getString("writer_id"),
					rs.getDate("regdate"),
					rs.getInt("hit")
				);	
			
		}	
		
		rs.close();
		st.close();
		con.close();	
		
		return notice;
	}

	@Override
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		
		int result = 0;
		
		String sql = "INSERT INTO NOTICE(ID,TITLE,CONTENT,WRITER_ID) "
				+ "VALUES(NOTICE_SEQ.NEXTVAL,?,?,'newlec')";
		
		String url = "jdbc:oracle:thin:@192.168.0.15:1521/xepdb1";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "\"newlec\"", "l4class");
			
		PreparedStatement st  = con.prepareStatement(sql);
		st.setString(1, notice.getTitle());
		st.setString(2, notice.getContent());
		
		result = st.executeUpdate();
		
		st.close();
		con.close();
		
		return result;
	}

	@Override
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		int result = 0;
		
		String sql = "UPDATE NOTICE SET TITLE=?, CONTENT=? WHERE ID=?";
		
		String url = "jdbc:oracle:thin:@192.168.0.15:1521/xepdb1";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "\"newlec\"", "l4class");
		
		PreparedStatement st  = con.prepareStatement(sql);
		st.setString(1, notice.getTitle());
		st.setString(2, notice.getContent());
		st.setInt(3, notice.getId());
		
		result = st.executeUpdate();
		
		st.close();
		con.close();
		
		return result;
	}

	@Override
	public int delete(int id) throws ClassNotFoundException, SQLException {
		int result = 0;
		
		String sql = "DELETE NOTICE WHERE ID=?";
		
		String url = "jdbc:oracle:thin:@192.168.0.15:1521/xepdb1";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "\"newlec\"", "l4class");
		
		PreparedStatement st  = con.prepareStatement(sql);		
		st.setInt(1, id);
		
		result = st.executeUpdate();
		
		st.close();
		con.close();
		
		return result;
	}

	@Override
	public int getLastId() throws ClassNotFoundException, SQLException {

		int id = -1;
		
		String sql = "SELECT ID "
				+ "FROM (SELECT * FROM NOTICE ORDER BY REGDATE DESC) "
				+ "WHERE ROWNUM = 1";
		
		String url = "jdbc:oracle:thin:@192.168.0.15:1521/xepdb1";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "\"newlec\"", "l4class");
		Statement st  = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		if(rs.next()) {
			id = rs.getInt("id");
		}
		
		rs.close();
		st.close();
		con.close();
		
		return id;
	}

	@Override
	public int getCount() throws ClassNotFoundException, SQLException {
		
		return getCount("TITLE", "");
	}

	@Override
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException {

		int count = 0;
		
		String sql = "SELECT COUNT(ID) COUNT FROM NOTICE WHRE "+field+" LIKE ?";
		
		
		
		return count;
	}

}
