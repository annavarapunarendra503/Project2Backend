package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.BlogPostLikes;
import com.niit.model.Chat;
import com.niit.model.Friend;

import com.niit.model.Job;
import com.niit.model.Notifications;
import com.niit.model.ProfilePicture;
import com.niit.model.UsersDetails;

public class DBConfig {

Logger logger =LoggerFactory.getLogger(DBConfig.class);
	
	@Bean(name = "dataSource")
		public DataSource getDataSource() {
		logger.info("Data Source Configuration ");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("socialweb");
		dataSource.setPassword("asus");
		System.out.println("Datasource");
		logger.info("Data Base Connected ");
		return dataSource;

	}

	private Properties getHibernateProperties() {
		logger.info("========Hibernate Properties=========== ");
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.format_sql","true");
		System.out.println("Hibernate Properties");
		logger.info("========Hibernate Properties  has been set=========== ");
		return properties;

	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		logger.info("========Hibernate Session Factory=========== ");
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClasses(UsersDetails.class);
		sessionBuilder.addAnnotatedClasses(BlogPost.class);
		sessionBuilder.addAnnotatedClass(BlogComment.class);
		sessionBuilder.addAnnotatedClass(BlogPostLikes.class);
		sessionBuilder.addAnnotatedClass(Notifications.class);
		sessionBuilder.addAnnotatedClass(ProfilePicture.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(Chat.class);
		sessionBuilder.scanPackages("com.niit");
		System.out.println("Session");
	
	
		logger.info("========Hibernate SessionFactory Object created=========== ");
		return sessionBuilder.buildSessionFactory();

	}

	@Autowired
	@Bean(name = "transactionManager")
 	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		logger.info("========Hibernate Transaction =========== ");
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		System.out.println("Transaction");
		logger.info("========Hibernate Transaction object created=========== ");
		return transactionManager;
	}
	
	
	
	
}
