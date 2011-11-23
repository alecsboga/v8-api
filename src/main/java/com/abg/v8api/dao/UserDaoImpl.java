package com.abg.v8api.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abg.v8api.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

	private SimpleJdbcTemplate simpleJdbcTemplate;

	@Autowired
	public void init(DataSource dataSource) {
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	public void add(User user) {
		simpleJdbcTemplate.update("insert into user (name, email) values(:name, :email)", new MapSqlParameterSource(
		        "name", user.getName()).addValue("email", user.getEmail()));
	}

	public User findByEmail(String email) {
		return simpleJdbcTemplate.queryForObject("select name, email from user where email = ?", new UserRowMapper(),
		        email);
	}

	public List<User> getAll() {
		return simpleJdbcTemplate.query("select name, email from user", new UserRowMapper());
	}

	public void update(User person) {
		simpleJdbcTemplate.update("update user set name = :name where email = :email", new MapSqlParameterSource(
		        "name", person.getName()).addValue("email", person.getEmail()));
	}

	public void delete(String email) {
		simpleJdbcTemplate.update("delete from user where email = ?", email);
	}

	private class UserRowMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new User(rs.getString("name"), rs.getString("email"));
		}

	}
}
