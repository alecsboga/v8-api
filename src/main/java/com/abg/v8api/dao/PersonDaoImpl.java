package com.abg.v8api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.abg.v8api.domain.Person;


@Repository
public class PersonDaoImpl implements PersonDao {

	private SimpleJdbcTemplate simpleJdbcTemplate;
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	@Autowired
	public void init(DataSource dataSource) {
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
		this.namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public long add(Person person) {
		KeyHolder holder = new GeneratedKeyHolder();
		namedParamJdbcTemplate
				.update("insert into person (name, email, languageId) values(:name, :email, :languageId)",
						new MapSqlParameterSource("id", person.getId())
								.addValue("name", person.getName())
								.addValue("email", person.getEmail())
								.addValue("languageId", person.getLanguageId()), holder);
		return holder.getKey().longValue();
	}

	public Person findById(Long id) {
		return simpleJdbcTemplate.queryForObject(
				"select id, name, email, languageId from person where id = ?",
				new PersonRowMapper(), id);
	}

	public List<Person> findAll() {
		return simpleJdbcTemplate.query(
				"select id, name, email, languageId from person",
				new PersonRowMapper());
	}

	public void save(Person person) {
		simpleJdbcTemplate
				.update("update person set name = :name, email = :email, languageId = :languageId where id = :id",
						new MapSqlParameterSource("id", person.getId())
								.addValue("name", person.getName())
								.addValue("email", person.getEmail())
								.addValue("languageId", person.getLanguageId()));
	}

	public void delete(Long id) {
		simpleJdbcTemplate.update("delete from person where id = ?", id);
	}

	private class PersonRowMapper implements RowMapper<Person> {

		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getLong("id"));
			person.setLanguageId(rs.getLong("languageId"));
			person.setName(rs.getString("name"));
			person.setEmail(rs.getString("email"));
			return person;
		}

	}
}
