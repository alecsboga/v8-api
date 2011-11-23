package org.abg.v8api.json;

import java.io.IOException;

import org.abg.v8api.domain.AbstractEntity;
import org.abg.v8api.exception.ApplicationException;
import org.abg.v8api.exception.SystemException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class JsonConverter<T extends AbstractEntity> {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonConverter.class);
	private ObjectMapper mapper = new ObjectMapper();

	public T jsonToJava(String json, Class<T> target) throws ApplicationException, SystemException {
		try {
			return this.mapper.readValue(json, target);
		} catch (JsonParseException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException("Error parsing json.", e);
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException("Error mapping json.", e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new SystemException(e);
		}
	}

	public String javaToJson(T pojo) throws ApplicationException, SystemException {
		try {
			return this.mapper.writeValueAsString(pojo);
		} catch (JsonGenerationException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException("Error generating json.", e);
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage(), e);
			throw new ApplicationException("Error mapping json.", e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new SystemException(e);
		}
	}

}
