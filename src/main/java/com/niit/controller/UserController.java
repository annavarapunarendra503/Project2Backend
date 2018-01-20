package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.niit.dao.UserDao;
import com.niit.model.UsersDetails;
import com.niit.service.UserService;
//import com.niit.model.Error;

@RestController
public class UserController {

	
	@Autowired
	private UserService userService;
	
	

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody UsersDetails user) {
	
if(!userService.isUsernameValid(user.getUsername()))
		{
			Error error = new Error(user.getUsername()+"..username already exists,, please enter different username");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
		}
		
	 if (!userService.isEmailValid(user.getEmail()))
	 {
			Error error = new Error(user.getEmail()+"...Email address already exists,, please enter different email");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
	}

		boolean result = userService.saveOrUpdate(user);
		if (result) {
			return new ResponseEntity<UsersDetails>(user, HttpStatus.OK);
		} else {
			Error error = new Error("unable to register user details");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/************************Login***********************************/
	@SuppressWarnings("unused")
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody UsersDetails users,HttpSession session){
	
		
	    UsersDetails validUser=userService.login(users.getUsername(),users.getPassword());
	    System.out.println(validUser.getUsername());
	    System.out.println("Password = "+users.getPassword());
		
	    if(validUser==null)

	    {
		    Error error=new Error("Invalid username and password.. please enter valid credentials");
		    return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}	   
	    else	
	    {
	        validUser.setOnline(true);
		    validUser=userService.updateUser(validUser);
		    session.setAttribute("validUser", validUser);
		    return new ResponseEntity<UsersDetails>(validUser,HttpStatus.OK);    
		}
	   
	}
	//************************Logout ***********************************//*
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ResponseEntity<?> logout(HttpSession session)
	{ 
	  
	    UsersDetails validUser=(UsersDetails) session.getAttribute("validUser");
	    if(validUser==null)

	    {
	    	Error error=new Error("Unauthorized user");
		    return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}	   
	    else	
	    {
	        validUser.setOnline(false);
	        userService.updateUser(validUser);
		    session.removeAttribute("validUser");
		    session.invalidate();
		    return new ResponseEntity<UsersDetails>(validUser,HttpStatus.OK);    
		}
	}

/******************Listing the User Details****************************/
	
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	   public ResponseEntity<List<UsersDetails>> listAllUsers() {
        List<UsersDetails> users = userService.UserList();
        if(users.isEmpty()){
            return new ResponseEntity<List<UsersDetails>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UsersDetails>>(users, HttpStatus.OK);
    }
  
	@RequestMapping(value="/updateprofile",method=RequestMethod.PUT)
	public ResponseEntity<?> updateUserProfile(@RequestBody UsersDetails users,HttpSession session)
	{    
	    UsersDetails user=(UsersDetails)session.getAttribute("user");
	    if(user==null)
	    {
	        Error error=new Error("Unauthorized user");
	        return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED); 
	    }
	    userService.updateUser(users);
	    session.setAttribute("user", users);
	    return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}

