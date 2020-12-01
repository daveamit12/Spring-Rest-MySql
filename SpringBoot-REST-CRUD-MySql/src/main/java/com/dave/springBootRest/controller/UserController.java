package com.dave.springBootRest.controller;

import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dave.springBootRest.exception.ResourceNotFoundException;
import com.dave.springBootRest.model.UserModel;
import com.dave.springBootRest.repository.UserRepo;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired 
	private UserRepo userRepo;

	
	//get all users api.
	@GetMapping
	public List<UserModel> getAllUser(){
		
		return this.userRepo.findAll();
		
		
	} 
	
	
	//get by id,
	@GetMapping("/{id}")
	public UserModel getAllUser(@PathVariable(value = "id") long userId){
		
		return this.userRepo.findById(userId).
				orElseThrow(() -> new ResourceNotFoundException("user not present with given id"+userId));
		
		
	}
	//create,
	@PostMapping
	public UserModel createUser(@RequestBody UserModel user){
		
		return this.userRepo.save(user);
	}
	
	//update api 
	@PutMapping("/{id}")
	public UserModel updateUser(@RequestBody UserModel user,@PathVariable("id") long userId) {
		
		UserModel existingUser=userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found by id"+userId));
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return this.userRepo.save(existingUser);
		
	}
	//then delete user by id api.
	@DeleteMapping("/{id}")
	public ResponseEntity<UserModel> deleteUser(@PathVariable("id") long uId) {
		UserModel exstUser=this.userRepo.findById(uId).
				orElseThrow(() -> new ResourceNotFoundException("user not present with given id"+uId));
		 this.userRepo.delete(exstUser);
		 return ResponseEntity.ok().build();
		
	}
	
}
