package com.demoapp.utilities;

import com.demoapp.entities.User;
import com.demoapp.entities.UserResponse;

public class UserMapper {

	public UserMapper() {
		// TODO Auto-generated constructor stub
	}
	
	public static UserResponse mapUserToUserResponse(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setUserId(user.getUserId());
		userResponse.setUsername(user.getUsername());
		userResponse.setPassword(user.getPassword());
		userResponse.setEmail(user.getEmail());
		return userResponse;
	}
	
}
