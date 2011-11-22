package com.abg.v8api.dao;

import java.util.List;

import com.abg.v8api.domain.Person;


public interface PersonDao {
	
	long add(Person customer);
	
	Person findById(Long id);
	
	List<Person> findAll();
	
	void save(Person customer);
	
	void delete(Long id);

}
