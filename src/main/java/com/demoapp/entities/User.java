package com.demoapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "userId")
	private Integer userId;
	
	@Column(name = "username")
	@NotBlank(message = "username cannot be empty")
	private String username;
	
	@Column(name = "password")
	@Pattern(regexp = "^[a-zA-Z0-9/./_/-/$/#/@/!]{8,30}$", message="Password must be minimum 8 and maximum 30 chars long(special chars allowed - .,_,-,$,#,!,@)")
	private String password;
	
	@Column(name = "email")
	@Pattern(regexp = "^([a-zA-Z]+)([a-zA-Z0-9/./_/-]*)@([a-zA-Z]+).([a-zA-Z]{2,3}).([a-zA-Z]{2,3})?$" , message = "Invalid Email(characters,numbers, . , _ and - are allowwed) Eg., abc.someone12@xyz.com")
	private String email;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer userId, String username, String password, String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ "]";
	}
	
	
	
}
