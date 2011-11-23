package org.abg.v8api.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

public class User extends AbstractEntity {

	private static final long serialVersionUID = -9053920423914662633L;

	private String name;
	private String email;

	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
