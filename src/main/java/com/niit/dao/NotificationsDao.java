package com.niit.dao;

import java.util.List;

import com.niit.model.Notifications;

public interface NotificationsDao {

	public List<Notifications>getNotification(String username,int viewed);
    public Notifications updateNotification(int notificationId);
	
}
