package com.spring.security.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.security.models.User;


@Service
public class UserService {
	
	List<User> list = new ArrayList<>();

	public UserService() {
		list.add(new User("rahul","rahul","rahul@gmail.com"));
		list.add(new User("chayan","chayan","chayan@gmail.com"));
		list.add(new User("prateek","prateek","prateek@gmail.com"));
	}
	
	
	//get all users
	
	public List<User> getAllUsers(){
		
		return this.list;
	}
	
	//get single user
	public User getUser(String userName) {
		return this.list.stream().filter((user)->user.getUserName().equals(userName)).findAny().orElse(null);
	}
	
	
	//add new user
	public User addUser(User user) {
		this.list.add(user);
		return user;
	}

}
