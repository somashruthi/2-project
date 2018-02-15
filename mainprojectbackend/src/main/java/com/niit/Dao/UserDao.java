package com.niit.Dao;

import com.niit.model.User;

public interface UserDao {
	void registerUser(User user);
	boolean isEmailUnique(String email); 

}
