package com.backend.fullstackbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.fullstackbackend.exception.UserNotFoundAdvice;
import com.backend.fullstackbackend.exception.UserNotFoundException;
import com.backend.fullstackbackend.model.User;
import com.backend.fullstackbackend.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/users")
	User newUser(@RequestBody User newuser) {
		return userRepository.save(newuser);
	}
	
	@GetMapping("/users")
	List<User> getAllUsers()
	{
		return userRepository.findAll();
		}
	
	@GetMapping("/user/{id}")
	User getUserById(@PathVariable Long id)
	{
		return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
		     
	}
	
	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User newUser,@PathVariable Long id)
	{
		return userRepository.findById(id).map(user->{
			             user.setUsername(newUser.getUsername());
			             user.setName(newUser.getName());
			             user.setEmail(newUser.getEmail());
			             return userRepository.save(user);
		}).orElseThrow(()->new UserNotFoundException(id));
	}
	
	@DeleteMapping("/user/{id}")
	String deteteUser(@PathVariable Long id) {
		if(!userRepository.existsById(id))
		{
			throw new UserNotFoundException(id);
		}
		
	    userRepository.deleteById(id);
	    return "user with id " + id + " has been deleted succesfully";
		
	}


	

}
