package com.app.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.user.exceptionHandling.ResourceNotFoundException;
import com.app.user.model.User;
import com.app.user.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
@Autowired
private UserRepository userRepo;
public List<User> getAllUser()
{
	return this. userRepo.findAll();
}
@RequestMapping("/{id}")
public User getById(@PathVariable (value="id") long id)
{
	return this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found with "+ id));
}
@PostMapping
public User save(@Valid @RequestBody User user)
{
return this.userRepo.save(user);	
}
@PutMapping("/{id}")
public User updateById(@Valid @PathVariable (value="id") Long id,@RequestBody User user)
{
	User existing=  this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found with "+id));
existing.setEmail(user.getEmail());
existing.setName(user.getName());
existing.setPassword(user.getPassword());
return this.userRepo.save(existing);
}
}
