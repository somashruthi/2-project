package com.niit.Dao;

import java.util.List;

import javax.persistence.Query;
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
	/*public List<Job> getAllJobs() {
		 		
		 		Session session=sessionFactory.getCurrentSession();
		 		Query query=session.createQuery("from Job");
		 		return query.list();
		 	}
		 
			public Job getJob(int id) {
		 		
		 		Session session=sessionFactory.getCurrentSession();
		 		Job job=(Job)session.get(Job.class,id);
		 		return job;
		 	}
		 	
		 	*/
	public List<Job> getAllJobs() {
		
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Job");
 		return query.list();
	
	}
	public Job getJob(int id) {
		Session session=sessionFactory.getCurrentSession();
 		Job job=(Job)session.get(Job.class,id);
 		return job;
		
	}
}
