package com.niit.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Dao.JobDao;
import com.niit.Dao.UserDao;
import com.niit.model.Errorclass;
import com.niit.model.Job;
import com.niit.model.User;

import antlr.collections.List;

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
			job.setPostedOn(new Date());
			jobDao.addJob(job);
			return new ResponseEntity<Job>(job,HttpStatus.OK);
		}catch(Exception e){
		
			Errorclass error=new Errorclass(64,"Unable to post job details"+e.getMessage());
			return new ResponseEntity<Errorclass>(error,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	
	@RequestMapping(value="/alljobs",method=RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session){
		//AUTHENTICATION
				String email=(String)session.getAttribute("loginId");
				if(email==null)
				{
					Errorclass error=new Errorclass(67,"Unauthorized access");
					return new ResponseEntity<Errorclass>(error,HttpStatus.UNAUTHORIZED);
				}
		List<Job>jobs=jobDao.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}

	
@RequestMapping(value="/getjob{id}",method=RequestMethod.GET)
public ResponseEntity<?> getJob(@PathVariable int id,HttpSession session){
	//AUTHENTICATION
			String email=(String)session.getAttribute("loginId");
			if(email==null)
			{
				Errorclass error=new Errorclass(67,"Unauthorized access");
				return new ResponseEntity<Errorclass>(error,HttpStatus.UNAUTHORIZED);
			}
			Job job=jobDao.getJob(id);
	return new ResponseEntity<Job>(job,HttpStatus.OK);
}
	
}
