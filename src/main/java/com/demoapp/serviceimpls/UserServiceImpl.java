package com.demoapp.serviceimpls;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

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

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


	@Override
	public ResponseEntity<List<UserResponse>> getAllAvailableUsers() {
		Iterable<User> iterable = userJpa.findAll();

		List<User> list = new ArrayList<>();
		iterable.forEach(list::add);
		List<UserResponse> userList = list.stream().map(e->
		{
			return UserMapper.mapUserToUserResponse(e);
		}
		).collect(Collectors.toList());

		return new ResponseEntity<>((userList),HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<UserResponse> saveNewUser(User user) {
	    //String randomUserId = UUID.randomUUID().toString();
		//to manually provide a random and unique userId to each user
		User savedUser = userJpa.save(user);
		UserResponse userResponse = UserMapper.mapUserToUserResponse(savedUser);

		return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<DeleteUserResponseBody> deleteAUser(Integer userId) throws UserByIdNotFoundException {
		Optional<User> user = userJpa.findById(userId);
	    if(user.isPresent()) {
	    	userJpa.deleteById(userId);
	    	DeleteUserResponseBody dURB = new DeleteUserResponseBody(200,"User with id = "+userId+" is deleted succesfully.");
	    	return new ResponseEntity<DeleteUserResponseBody>(dURB,HttpStatus.OK);
	  
	    }else {
	        throw new UserByIdNotFoundException("User not found with id = "+userId);
	    }
		

	}

	@Override
	public ResponseEntity<UserResponse> updateUser(Integer userId,Map<String,Object> userData) throws UserByIdNotFoundException {

		Optional<User> userRes = userJpa.findById(userId);

		if(userRes.isPresent()) {
			logger.info("user is present = {}",userRes);
			userData.forEach((key,value)->{
				Field field = ReflectionUtils.findField(User.class, key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, userRes.get(), value);
			});
			logger.info("user data after update = {}",userRes.get());
			UserResponse userResponse = UserMapper.mapUserToUserResponse(userRes.get());
			return new ResponseEntity<>(userResponse,HttpStatus.OK);

		}else {
			throw new UserByIdNotFoundException("User not found with id = "+userId);
		}
	}

	@Override
	public ResponseEntity<UserResponse> getUserById(Integer userId) throws UserByIdNotFoundException{
		Optional<User> optionalUser = userJpa.findById(userId);
		if(optionalUser.isPresent()) {
			UserResponse ures = UserMapper.mapUserToUserResponse(optionalUser.get());
			return new ResponseEntity<>(ures,HttpStatus.FOUND);
		}else {
//			return new ResponseEntity<>(new UserResponse(),HttpStatus.INTERNAL_SERVER_ERROR);
			throw new UserByIdNotFoundException("User not found with id "+userId);
		}
	}



}
