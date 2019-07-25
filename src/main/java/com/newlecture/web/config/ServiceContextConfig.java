package com.newlecture.web.config;

import java.io.IOException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

//@Configuration
@ComponentScan(basePackages= {"com.newlecture.web.dao.mybatis"
													,"com.newlecture.web.dao.java"})
public class ServiceContextConfig {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@192.168.0.15:1521/xepdb1");
		dataSource.setUsername("\"newlec\"");
		dataSource.setPassword("l4class");
		
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
		
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory
			.setMapperLocations(
					applicationContext
						.getResources("classpath:com/newlecture/web/dao/mybatis/mapper/*.xml"));
		
		return sqlSessionFactory;
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws IOException, Exception {
		SqlSessionFactory factory = sqlSessionFactoryBean().getObject();
		
		SqlSessionTemplate sqlSession = new SqlSessionTemplate(factory);
		
		return sqlSession;
	}
}
