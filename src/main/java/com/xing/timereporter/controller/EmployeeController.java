package com.xing.timereporter.controller;

import com.xing.timereporter.model.Employee;
import com.xing.timereporter.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	private final EmployeeRepo employeeRepo;

	public EmployeeController(@Autowired EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
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

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Employee> addEmployee(Employee employee) {
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
