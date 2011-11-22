package com.abg.v8api.service;

import java.util.List;

import com.abg.v8api.domain.Person;

public interface PersonService {

	List<Person> getAll();

	void add(Person newPerson);

	Person findById(Long id);

	void save(Person person);

	void delete(Long id);
	
}