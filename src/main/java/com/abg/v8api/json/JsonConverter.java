package com.abg.v8api.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.abg.v8api.domain.AbstractEntity;

public class JsonConverter<T extends AbstractEntity> {

	private ObjectMapper mapper = new ObjectMapper();

	public T jsonToJava(String json, Class<T> target) throws JsonParseException, JsonMappingException, IOException {
		return this.mapper.readValue(json, target);
	}

	public String javaToJson(T pojo) throws JsonGenerationException, JsonMappingException, IOException {
		return this.mapper.writeValueAsString(pojo);
	}

}
