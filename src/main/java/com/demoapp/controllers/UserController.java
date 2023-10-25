package com.demoapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demoapp.entities.User;
import com.demoapp.entities.UserResponse;
import com.demoapp.exceptionsmapp.UserByIdNotFoundException;
import com.demoapp.serviceimpls.UserServiceImpl;
import com.demoapp.utilities.DeleteUserResponseBody;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserResponse>> getAllUsers(){
		 
		return userServiceImpl.getAllAvailableUsers();
	}
	
	@GetMapping("/user/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("userId") Integer userId) throws UserByIdNotFoundException{
		 
		return userServiceImpl.getUserById(userId);
	}
	
	@PostMapping("/user")
	public ResponseEntity<UserResponse> saveNewUser(@Valid @RequestBody User user)
	{ 
		return userServiceImpl.saveNewUser(user);
	}
	
	@PutMapping("/user")
	public ResponseEntity<UserResponse> updateUser(@RequestBody User user)
	{
		return userServiceImpl.updateUser(user);
	}
	
	@DeleteMapping("/user/{userId}")
    public ResponseEntity<DeleteUserResponseBody> deleteAUser(@PathVariable("userId") Integer userId){
		
		return userServiceImpl.deleteAUser(userId);
		
	}
}
