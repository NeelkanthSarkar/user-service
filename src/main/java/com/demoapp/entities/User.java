package com.demoapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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
	@Pattern(regexp = "^[a-zA-Z0-9._-]{8,30}$", message="Password must be minimum 8 and maximum 30 chars long")
	private String password;
	
	@Column(name = "email")
	@Email(message = "Invalid Email address")
	@NotBlank(message = "Email cannot be empty")
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