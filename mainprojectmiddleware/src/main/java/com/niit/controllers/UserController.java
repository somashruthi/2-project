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
	public ResponseEntity<?> registerUser(@RequestBody User user){
		userdao.registerUser(user);
		return  new ResponseEntity(user,HttpStatus.OK);
	}
}