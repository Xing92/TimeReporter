package com.xing.timereporter.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xing.timereporter.model.Employee;
import com.xing.timereporter.model.User;
import com.xing.timereporter.repository.EmployeeRepo;
import com.xing.timereporter.repository.UserRepo;


@RestController
@RequestMapping("/view")
public class SimpleController {


	private final EmployeeRepo employeeRepo;
	private final UserRepo userRepo;
	
	public SimpleController(@Autowired EmployeeRepo employeeRepo, UserRepo userRepo) {
		this.employeeRepo = employeeRepo;
		this.userRepo = userRepo;
	}


	@RequestMapping(method = RequestMethod.GET, value = "all")
	public List<Employee> getEmployee() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByUsername(auth.getName());
        System.out.println(auth.getName());
		return employeeRepo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "special")
	public List<Employee> getEmployeeS() {
		return employeeRepo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "register")
	public User registerUser(User user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setUsername(user.getUsername());
		userRepo.save(newUser);
		System.out.println("Registered new user");
		return newUser;
	}
}
