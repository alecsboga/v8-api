package org.abg.v8api.dao;

import java.util.List;

import org.abg.v8api.domain.User;



public interface UserDao {
	
	void add(User user);
	
	User findByEmail(String email);
	
	List<User> getAll();
	
	void update(User user);
	
	void delete(String email);

}
