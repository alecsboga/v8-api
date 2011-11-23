package com.abg.v8api.jms;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;

public class MessageConsumer implements SessionAwareMessageListener<TextMessage> {

	public void onMessage(TextMessage msg, Session session) throws JMSException {
//		System.out.println("received message: " + msg); //lots of details printed
		System.out.println("received message: " + msg.getText());
	}

}
