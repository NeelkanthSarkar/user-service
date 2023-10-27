package com.demoapp.serviceimpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demoapp.datajpa.UserJPA;
import com.demoapp.entities.User;
import com.demoapp.entities.UserResponse;
import com.demoapp.exceptionsmapp.UserByIdNotFoundException;
import com.demoapp.services.UserService;
import com.demoapp.utilities.DeleteUserResponseBody;
import com.demoapp.utilities.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserJPA userJpa;
	
	
	@Override
	public ResponseEntity<List<UserResponse>> getAllAvailableUsers() {
		Iterable<User> iterable = userJpa.findAll();

		List<User> list = new ArrayList<User>();
		iterable.forEach(list::add);
		List<UserResponse> userList = list.stream().map(e->
		{
			return UserMapper.mapUserToUserResponse(e,new UserResponse());
		}
		).collect(Collectors.toList());
		
		return new ResponseEntity<>((userList),HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<UserResponse> saveNewUser(User user) {
	    //String randomUserId = UUID.randomUUID().toString();
		//to manually provide a random and unique userId to each user
		User savedUser = userJpa.save(user);
		UserResponse userResponse = UserMapper.mapUserToUserResponse(savedUser, new UserResponse());
		
		return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<DeleteUserResponseBody> deleteAUser(Integer userId) {
		Optional<User> user = userJpa.findById(userId);
	    if(user.isPresent()) {
	    	userJpa.delete(user.get());  
	    	return new ResponseEntity<>(new DeleteUserResponseBody("success"),HttpStatus.OK);
	    }else {
	    	return new ResponseEntity<>(new DeleteUserResponseBody("UserId does not exists"),HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	
	}

	@Override
	public ResponseEntity<UserResponse> updateUser(User user) {
		User upUser = userJpa.save(user);
	    UserResponse userResponse = UserMapper.mapUserToUserResponse(upUser, new UserResponse());
		return new ResponseEntity<>(userResponse,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserResponse> getUserById(Integer userId) throws UserByIdNotFoundException{
		Optional<User> optionalUser = userJpa.findById(userId);
		if(optionalUser.isPresent()) {
			UserResponse ures = UserMapper.mapUserToUserResponse(optionalUser.get(),new UserResponse());
			return new ResponseEntity<>(ures,HttpStatus.FOUND);
		}else {
//			return new ResponseEntity<>(new UserResponse(),HttpStatus.INTERNAL_SERVER_ERROR);
			throw new UserByIdNotFoundException("User not found with id "+userId);
		}
	}
	

}
