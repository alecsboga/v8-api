package com.abg.v8api.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.abg.v8api.domain.Person;

public class JsonConverterTest {

	@Test
	public void testJsonToJava() {
		try {
			Person person = new JsonConverter<Person>().jsonToJava(getJsonPerson("al", 10, 10, "al@gmail.com"),
			        Person.class);

			assertEquals("al", person.getName());
			assertTrue(10 == person.getId());
			assertTrue(10 == person.getLanguageId());
			assertEquals("al@gmail.com", person.getEmail());

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testJavaToJson() {
		Person inPerson = getTestPerson();
		try {
			String json = new JsonConverter<Person>().javaToJson(inPerson);

			Person outPerson = new JsonConverter<Person>().jsonToJava(json, Person.class);

			assertEquals(inPerson.getName(), outPerson.getName());
			assertEquals(inPerson.getId(), outPerson.getId());
			assertEquals(inPerson.getLanguageId(), outPerson.getLanguageId());
			assertEquals(inPerson.getEmail(), outPerson.getEmail());

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getJsonPerson(String name, int id, int languageId, String email) {
		return "{\"name\":\"" + name + "\",\"id\":" + id + ",\"languageId\":" + languageId + ",\"email\":\"" + email
		        + "\"}";
	}

	private Person getTestPerson() {
		Person newPerson = new Person();
		newPerson.setId(10L);
		newPerson.setName("gigi");
		newPerson.setEmail("gigi@yahoo.com");
		newPerson.setLanguageId(11L);
		return newPerson;
	}

}
