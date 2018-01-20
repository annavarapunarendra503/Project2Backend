package com.niit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.dao.FriendDao;
import com.niit.model.Friend;
import com.niit.model.UsersDetails;

@Service
public class FriendServiceImpl implements FriendService{
	
	
	@Autowired
	
	FriendDao friendDao;

	public void addFriendRequest(Friend friend) {
		friendDao.addFriendRequest(friend);
		
	}

	public List<UsersDetails> suggestedUserList(String username) {
		
		return friendDao.suggestedUserList(username);
	}

	public List<Friend> pendingRequests(String username) {


		return friendDao.pendingRequests(username);
	}

	public void updatePendingRequest(Friend friend) {
		friendDao.updatePendingRequest(friend);
		
	}

	public List<UsersDetails> listofFriends(String username) {
		
		return friendDao.listofFriends(username);
	}

	public List<UsersDetails> listofMutualFriends(String loginId, String suggestedUsername) {
		// TODO Auto-generated method stub
		return null;
	}

}
