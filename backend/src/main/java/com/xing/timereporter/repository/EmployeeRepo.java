package com.xing.timereporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xing.timereporter.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
}
