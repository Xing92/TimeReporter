package com.xing.timereporter.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xing.timereporter.model.Employee;
import com.xing.timereporter.model.User;
import com.xing.timereporter.repository.EmployeeRepo;
import com.xing.timereporter.repository.UserRepo;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	private final EmployeeRepo employeeRepo;
	private final UserRepo userRepo;

	public EmployeeController(@Autowired EmployeeRepo employeeRepo, UserRepo userRepo) {
		this.employeeRepo = employeeRepo;
		this.userRepo = userRepo;
	}

	@RequestMapping(method = RequestMethod.GET, value = "all")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeRepo.findAll();
		if (employees.isEmpty()) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value="{id}")
	public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable("id") Long id) {
		Optional<Employee> employee = employeeRepo.findById(id);
		if (employee == null) {
			return new ResponseEntity<Optional<Employee>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Optional<Employee>>(employee, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "me")
	public ResponseEntity<Employee> getEmployeeMe() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByUsername(userName);
		System.out.println("XING:YATA");
		if (user == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		Employee employee = user.getEmployee();
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Employee> addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("pesel") String pesel) {
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setPesel(pesel);
		employee.setId(1L);
		Employee savedEmployee = employeeRepo.save(employee);
		System.out.println(savedEmployee);
		return new ResponseEntity<Employee>(savedEmployee, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value="{id}")
	public ResponseEntity deleteEmployee(@PathVariable("id") Long id) {
		if (employeeRepo.existsById(id)) {
			employeeRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
