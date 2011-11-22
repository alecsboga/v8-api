package com.abg.v8api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abg.v8api.dao.PersonDao;
import com.abg.v8api.domain.Person;
import com.abg.v8api.jms.MessagePublisher;


@Service
public class PersonServiceImpl implements PersonService {


	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private MessagePublisher messagePublisher;

	@Transactional(readOnly = true)
	public List<Person> getAll() {
		List<Person> customerList = personDao.findAll();
		return customerList;
	}

	@Transactional
	public void add(Person person) {
		long id = personDao.add(person);
		person.setId(id);
		messagePublisher.pushPersonOnQueue(person);
	}

	@Transactional(readOnly = true)
	public Person findById(Long id) {
		return personDao.findById(id);
	}

	@Transactional
	public void save(Person person) {
		personDao.save(person);
	}

	@Transactional
	public void delete(Long id) {
		personDao.delete(id);
	}
}
