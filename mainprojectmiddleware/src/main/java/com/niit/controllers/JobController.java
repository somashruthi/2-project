package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Dao.JobDao;
import com.niit.Dao.UserDao;
import com.niit.model.Errorclass;
import com.niit.model.Job;
import com.niit.model.User;

@Controller
public class JobController
{
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JobDao jobDao;
	
	@RequestMapping(value="/addjob",method=RequestMethod.POST)
	public ResponseEntity<?> addJob(@RequestBody Job job,HttpSession session)
	{
		//AUTHENTICATION
		String email=(String)session.getAttribute("loginId");
		if(email==null)
		{
			Errorclass error=new Errorclass(67,"Unauthorized access");
			return new ResponseEntity<Errorclass>(error,HttpStatus.UNAUTHORIZED);
		}
		
		//user is authenticated
		User user=userDao.getUser(email);
		if(!user.getRole().equals("ADMIN"))
		{
			Errorclass error=new Errorclass(63,"Acess Denied");
			return new ResponseEntity<Errorclass>(error,HttpStatus.UNAUTHORIZED);	
		}
		try{
			jobDao.addJob(job);
			return new ResponseEntity<Job>(job,HttpStatus.OK);
		}catch(Exception e){
		
			Errorclass error=new Errorclass(64,"Unable to post job details"+e.getMessage());
			return new ResponseEntity<Errorclass>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
}
