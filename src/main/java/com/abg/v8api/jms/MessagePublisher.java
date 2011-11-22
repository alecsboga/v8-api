package com.abg.v8api.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.abg.v8api.domain.Person;

@Service
public class MessagePublisher {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void pushPersonOnQueue(Person person) {
		jmsTemplate.convertAndSend(person.toString());
	}
}
