package com.niit.Dao;

import com.niit.model.User;

public interface UserDao {
	void registerUser(User user);
	boolean isEmailUnique(String email); 
	User login(User user); //returns 1 user object or null value
	// user takes two fields i.e email and password
	//User gives all fields /columns as o/p is email and password is matched
	void update(User validUser);
	User getUser(String email);
	void updateUser(User user);
}
