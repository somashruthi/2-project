package com.niit.Dao;

import javax.transaction.Transactional;

import org.h2.engine.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Job;

@Repository
@Transactional
public class JobDaoImpl implements JobDao {
	@Autowired
private SessionFactory sessionFactory;
	public void addJob(Job job) {
		Session session=sessionFactory.getCurrentSession();
		session.save(job);

	}

}
