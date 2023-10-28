package com.demoapp.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.demoapp.entities.User;
import com.demoapp.entities.UserResponse;
import com.demoapp.exceptionsmapp.UserByIdNotFoundException;
import com.demoapp.utilities.DeleteUserResponseBody;

public interface UserService {

	ResponseEntity<List<UserResponse>> getAllAvailableUsers();
	ResponseEntity<UserResponse> getUserById(Integer userId) throws UserByIdNotFoundException; 
	ResponseEntity<UserResponse> saveNewUser(User user);
	ResponseEntity<DeleteUserResponseBody> deleteAUser(Integer userId);
	ResponseEntity<UserResponse> updateUser(Integer userId,Map<String,Object> userData)throws UserByIdNotFoundException;
}
