package com.abg.v8api.json;

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
	        Person person = new JsonConverter<Person>().jsonToJava(getJson(), Person.class);
	        System.out.println(person.toString());
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
		Person person = getPerson();
		try {
			String json = new JsonConverter<Person>().javaToJson(person);

			System.out.println("json: " + json);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getJson() {
		return "{\"name\":\"gigi\",\"id\":55,\"languageId\":12654,\"email\":\"gigi.duru@yahoo.com\"}";
	}

	private Person getPerson() {
		Person newPerson = new Person();
		newPerson.setId(10L);
		newPerson.setName("gigi");
		newPerson.setEmail("gigi@yahoo.com");
		newPerson.setLanguageId(11L);
		return newPerson;
	}

}
