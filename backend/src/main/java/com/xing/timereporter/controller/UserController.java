package com.xing.timereporter.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xing.timereporter.model.Role;
import com.xing.timereporter.model.User;
import com.xing.timereporter.repository.UserRepo;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserRepo userRepo;

	public UserController(@Autowired UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@RequestMapping(method = RequestMethod.GET, value = "all")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userRepo.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") Long id) {
		Optional<User> user = userRepo.findById(id);
		if (user == null) {
			return new ResponseEntity<Optional<User>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "me")
	public ResponseEntity<User> getUserMe() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("name:" + userName);
		User user = userRepo.findByUsername(userName);
		System.out.println("user:" + user);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> addUser(User user) {
		User savedUser = userRepo.save(user);
		System.out.println(savedUser);
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "simple")
	public ResponseEntity<User> addSimpleUser(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam(name = "email", required = false) String email) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		Role role = new Role();
		role.setRole("USER");
		user.addRole(role);
		User savedUser = userRepo.save(user);
		System.out.println(savedUser);
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity deleteUser(@PathVariable("id") Long id) {
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

//	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(method = RequestMethod.GET, value = "/special")
	public ResponseEntity<String> getSpecialString() {
		return new ResponseEntity<String>("My Special String", HttpStatus.OK);
	}
}
