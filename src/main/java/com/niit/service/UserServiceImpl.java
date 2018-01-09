package com.niit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.dao.UserDao;
import com.niit.model.UsersDetails;


@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	
	private UserDao userDao;
	
	
	
	public boolean saveOrUpdate(UsersDetails users) {

		return userDao.saveOrUpdate(users);
	}
	
	public void delete(UsersDetails user) {
		userDao.delete(user);
		
	}

	public UsersDetails getUser(String username) {
		return userDao.getUser(username);
	}

	public UsersDetails viewUser(int userid) {
		
		return userDao.viewUser(userid);
	}

	public List<UsersDetails> UserList() {
	
		return userDao.UserList();
	}

	public UsersDetails login(String username,String password) {
		
		return userDao.login(username,password);
	}

	public boolean isUsernameValid(String username) {
	
		return userDao.isUsernameValid(username);
	}

	public boolean isEmailValid(String email) {
	
		return userDao.isEmailValid(email);
	}

	public UsersDetails updateUser(UsersDetails users) {

		return userDao.updateUser(users);
	}
	
	
	
}
