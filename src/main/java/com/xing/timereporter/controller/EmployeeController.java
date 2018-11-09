package com.xing.timereporter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xing.timereporter.model.Employee;

import org.springframework.stereotype.Controller;

@Controller(value="/api")
public class EmployeeController {
	
	@RequestMapping(method=RequestMethod.GET, value="/employees")
	public Employee getAllEmployees() {
		return new Employee();
	}

}
