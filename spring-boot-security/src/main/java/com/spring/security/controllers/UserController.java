package com.spring.security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.Services.UserService;
import com.spring.security.models.User;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService service;
	
	
	@GetMapping("/normal")
	public ResponseEntity<String> normalUser(){
		return ResponseEntity.ok("I am normal User");
	}
	
	@GetMapping("/admin")
	public ResponseEntity<String> adminUser(){
		return ResponseEntity.ok("I am admin User");
	}
	
	@GetMapping("/public")
	public ResponseEntity<String> publicUser(){
		return ResponseEntity.ok("I am public User");
	}
	
	//all users
	@GetMapping("/")
	public List<User> getAllUsers(){
		return this.service.getAllUsers();
	}
	
	//single user;
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username")String userName) {
		return this.service.getUser(userName);
	}
	
	//add user
	@PostMapping("/")
	public User addUser(@RequestBody User user) {
		return this.service.addUser(user);
	}

}
