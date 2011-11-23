package org.abg.v8api.service;

import java.util.List;

import org.abg.v8api.dao.UserDao;
import org.abg.v8api.domain.User;
import org.abg.v8api.exception.ApplicationException;
import org.abg.v8api.exception.SystemException;
import org.abg.v8api.jms.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MessagePublisher messagePublisher;

	@Transactional(readOnly = true)
	public List<User> getAll() {
		List<User> userList = userDao.getAll();
		return userList;
	}

	@Transactional
	public void add(User person) throws SystemException, ApplicationException {
		userDao.add(person);
		messagePublisher.pushOnQueue(person);
	}

	@Transactional(readOnly = true)
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Transactional
	public void save(User user) {
		userDao.update(user);
	}

	@Transactional
	public void delete(String email) {
		userDao.delete(email);
	}

}
