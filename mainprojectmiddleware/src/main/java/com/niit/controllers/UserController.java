package com.niit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Dao.UserDao;
import com.niit.model.Errorclass;
import com.niit.model.User;



@Controller
public class UserController
{
	@Autowired
	private UserDao userdao;          // UserController depends on UserDao interface
	public UserController()
	{
			System.out.println("User controller is insantiated");
		
	}
	
	@RequestMapping(value="/registeruser",method=RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user){       //user is from frontend
		
		if(!userdao.isEmailUnique(user.getEmail()))
		{

			Errorclass error=new Errorclass(1, "Email is already present....Please enter valid email address");
			return  new ResponseEntity<Errorclass>(error,HttpStatus.CONFLICT);
		
		}
		try{
		userdao.registerUser(user); // used for entering user , before entering id we are checking whether any email id exists or not
		
		}  
		catch(Exception e)
		{
			Errorclass error=new Errorclass(2,"All fields are required" +e.getMessage());
			return  new ResponseEntity<Errorclass>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return  new ResponseEntity(user,HttpStatus.OK);
	}
}   














