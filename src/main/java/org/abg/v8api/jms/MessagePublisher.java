package org.abg.v8api.jms;

import org.abg.v8api.domain.AbstractEntity;
import org.abg.v8api.domain.User;
import org.abg.v8api.exception.ApplicationException;
import org.abg.v8api.exception.SystemException;
import org.abg.v8api.json.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


@Service
public class MessagePublisher {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessagePublisher.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private JsonConverter<AbstractEntity> jsonConverter;

	public void pushOnQueue(User user) throws ApplicationException, SystemException {
		try {
			jmsTemplate.convertAndSend(jsonConverter.javaToJson(user));
		} catch (JmsException e) {
			LOGGER.error(e.getMessage(), e);
			throw new SystemException(e);
		}
	}
}
