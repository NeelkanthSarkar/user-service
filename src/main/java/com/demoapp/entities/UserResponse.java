package com.demoapp.entities;

public class UserResponse {

	private Integer userId;

	private String username;

	private String password;

	private String email;

	public UserResponse() {
		super();

	}

	public UserResponse(Integer userId, String username, String password, String email) {
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
