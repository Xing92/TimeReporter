package com.xing.timereporter.controller;

import com.xing.timereporter.model.Employee;
import com.xing.timereporter.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {


    private final EmployeeRepo employeeRepo;

    public EmployeeController(@Autowired EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employees/new")
    public void putAllEmployees() {
    	employeeRepo.save(new Employee());
    }
}
