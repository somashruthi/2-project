package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

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
		// check for duplicate email
		//if email is not unique,return error class object
		//if email is unique, then call register user method
		System.out.println(user.toString());
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
		return  new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user,HttpSession session){
		System.out.println("user");
		User validUser=userdao.login(user);
		System.out.println(" valid user");
		if(validUser==null){                  //invalid details
			Errorclass error=new Errorclass(7,"invalid username and password");
			
			return  new ResponseEntity<Errorclass>(error,HttpStatus.UNAUTHORIZED);
			//HttpStatus statusCode=HttpStatus.UNAUTHORIZED;
		}	
		
		else{	// valid details entered by the user
			validUser.setOnline(true);         //setting online ststus to true in frontend
			userdao.update(validUser);         // setting in dao
			session.setAttribute("loginId",user.getEmail());
			return new ResponseEntity<User>(validUser,HttpStatus.OK); 
		}
	}
	
	
	@RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session)           //session object is same for login and logout
	
	{
		String email=(String)session.getAttribute("loginId");
		if(email==null){                  //invalid details
			Errorclass error=new Errorclass(6,"please login");
			return new ResponseEntity<Errorclass>(error,HttpStatus.UNAUTHORIZED);
			
	}
	User user=userdao.getUser(email);
	user.setOnline(false); 
	userdao.update(user);      // update online status to false
	session.removeAttribute("loginId");
	session.invalidate();
	return new ResponseEntity<User>(user,HttpStatus.OK);
	
}   




}









