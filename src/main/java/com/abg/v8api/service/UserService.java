package com.abg.v8api.service;

import java.util.List;

import com.abg.v8api.domain.User;
import com.abg.v8api.exception.ApplicationException;
import com.abg.v8api.exception.SystemException;

public interface UserService {

	List<User> getAll();

	void add(User newPerson) throws SystemException, ApplicationException;

	User findByEmail(String email);

	void save(User person);

	void delete(String email);
	
}