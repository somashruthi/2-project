package com.niit.Dao;

import javax.transaction.Transactional;

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
		Session session=sessionFactory.getCurrentSession();
		session.save(user);      // insert into User1 values(email,name,password,lastname)
		

	}

}
