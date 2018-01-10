package com.niit.dao;

import com.niit.model.ProfilePicture;

public interface ProfilePictureDao {


	public ProfilePicture getProfilePicture(String username);
	public void saveProfilePicture(ProfilePicture profilePicture);
	
	
}
