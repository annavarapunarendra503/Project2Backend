/*package com.niit.Test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.Config.DBConfig;
import com.niit.Dao.UserDao;
import com.niit.Model.UsersDetails;

public class UsersTest {

static UserDao  userDao;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext();
		context.register(DBConfig.class);
		context.scan("com.niit");
		context.refresh();
		userDao=(UserDao)context.getBean("userDao");
	}
	
	@Test
	public void addUserTest()
	{
		UsersDetails user=new UsersDetails();
		//user.setUser_id(1);
		user.setFirstName("sathish");
		user.setLastName("kumar");
		user.setContact("9512368740");
		user.setEmail("sathish@gmail.com");
		user.setPassword("123");
		user.setRole("Admin");
		assertTrue("Problem in Inserting user", userDao.saveOrUpdate(user));	}

	
	
	
}
*/