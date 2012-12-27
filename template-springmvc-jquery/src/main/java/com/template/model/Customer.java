package com.template.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Customer {
	
	@NotNull
	String name;
	
	@NotNull
	@Pattern(regexp="^mypass$")
	String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
