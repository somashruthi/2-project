package com.niit.Dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.User;
@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	@Autowired
	
	private SessionFactory sessionFactory;
	 	public UserDaoImpl()
	 	{
	 		System.out.println("UserDaoimpl ha been started");
	 	}
	public void registerUser(User user) {
		
		System.out.println("registeruser in Usercontroller" + user);
		Session session=sessionFactory.getCurrentSession();
		session.save(user);      // insert into User1 values(email,name,password,lastname)
		

	}
	public boolean isEmailUnique(String email)         // email is input from new user
	{
		Session session=sessionFactory.getCurrentSession();	
		User user=(User)session.get(User.class,email);
		
		if (user==null)
			return true;
		else 
			return false;		
		
	}
	public User login(User user) {
		Session session=sessionFactory.getCurrentSession();
		                                              // email=0  & password=1
		Query query=session.createQuery("from User where email=? and password=?");
		query.setString(0,user.getEmail());
		query.setString(1,user.getPassword());
		return (User)query.uniqueResult();         // 1 or null vlue
		
			}
	public void update(User validUser) 
	{
		Session session=sessionFactory.getCurrentSession();	
		session.update(validUser); 
	}
	
	
	public User getUser(String email) {
		Session session=sessionFactory.getCurrentSession();	
		User user=(User)session.get(User.class,email);
		return user;
	}

}
