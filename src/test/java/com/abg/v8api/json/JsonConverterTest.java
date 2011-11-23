package com.abg.v8api.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.abg.v8api.domain.User;
import com.abg.v8api.exception.ApplicationException;
import com.abg.v8api.exception.SystemException;

public class JsonConverterTest {

	@Test
	public void testJsonToJava() {
		try {
			User user = new JsonConverter<User>().jsonToJava(getJsonUser("al", "al@gmail.com"), User.class);
			assertEquals("al", user.getName());

			assertEquals("al@gmail.com", user.getEmail());
		} catch (SystemException e) {
			fail(e.getMessage());
		} catch (ApplicationException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void testJavaToJson() {
		User inUser = getTestPerson();

		try {
			String json = new JsonConverter<User>().javaToJson(inUser);
			User outUser = new JsonConverter<User>().jsonToJava(json, User.class);

			assertEquals(inUser.getName(), outUser.getName());
			assertEquals(inUser.getEmail(), outUser.getEmail());
		} catch (SystemException e) {
			fail(e.getMessage());
		} catch (ApplicationException e) {
			fail(e.getMessage());
		}

	}

	private String getJsonUser(String name, String email) {
		return "{\"name\":\"" + name + "\",\"email\":\"" + email + "\"}";
	}

	private User getTestPerson() {
		return new User("gigi", "gigi@yahoo.com");
	}

}
