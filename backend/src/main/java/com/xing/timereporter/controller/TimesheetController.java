package com.xing.timereporter.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.xing.timereporter.model.Project;
import com.xing.timereporter.model.Timesheet;
import com.xing.timereporter.model.User;
import com.xing.timereporter.repository.EmployeeRepo;
import com.xing.timereporter.repository.ProjectRepo;
import com.xing.timereporter.repository.TimesheetRepo;
import com.xing.timereporter.repository.UserRepo;

@RestController
@RequestMapping("/api/timesheet")
public class TimesheetController {

	private final TimesheetRepo timesheetRepo;
	private final EmployeeRepo employeeRepo;
	private final ProjectRepo projectRepo;
	private final UserRepo userRepo;

	public TimesheetController(@Autowired TimesheetRepo timesheetRepo, @Autowired EmployeeRepo employeeRepo,
			@Autowired ProjectRepo projectRepo, @Autowired UserRepo userRepo) {
		this.timesheetRepo = timesheetRepo;
		this.employeeRepo = employeeRepo;
		this.projectRepo = projectRepo;
		this.userRepo = userRepo;
	}

	@RequestMapping(method = RequestMethod.GET, value = "all")
	public ResponseEntity<List<Timesheet>> getAllTimesheets() {
		List<Timesheet> timesheets = timesheetRepo.findAll();
		if (timesheets.isEmpty()) {
			return new ResponseEntity<List<Timesheet>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Timesheet>>(timesheets, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<Optional<Timesheet>> getTimesheetById(@PathVariable("id") Long id) {
		Optional<Timesheet> timesheet = timesheetRepo.findById(id);
		if (timesheet == null) {
			return new ResponseEntity<Optional<Timesheet>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Optional<Timesheet>>(timesheet, HttpStatus.OK);
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Timesheet> addTimesheet(Timesheet timesheet) {
//		Employee employee = employeeRepo.findById(timesheet.getId_employee()).get();
//		Project project = projectRepo.findById(timesheet.getId_project()).get();
//		employee.addTimesheet(timesheet);
//		project.addTimesheet(timesheet);
//		timesheet.setEmployee(employee);
//		timesheet.setProject(project);
//		Timesheet savedTimesheet = timesheetRepo.save(timesheet);
//		return new ResponseEntity<Timesheet>(savedTimesheet, HttpStatus.OK);
//	}

	@RequestMapping(method = RequestMethod.POST, value = "simple")
//	public ResponseEntity<Timesheet> addSimpleTimesheet(@RequestParam LocalDateTime timeStart, @RequestParam LocalDateTime timeEnd) {
	public ResponseEntity<Timesheet> addSimpleTimesheet(@RequestParam String startTime,
			@RequestParam String endTime, @RequestParam String projectName) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByUsername(userName);
		Employee employee = user.getEmployee();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
		LocalDateTime timeStart = LocalDateTime.parse(startTime, formatter);
		LocalDateTime timeEnd = LocalDateTime.parse(endTime, formatter);
		
		Timesheet timesheet = new Timesheet();
		timesheet.setTimeStart(timeStart);
		timesheet.setTimeEnd(timeEnd);
		timesheet.setEmployee(employee);

		Project project = projectRepo.findByName(projectName);
		if(project == null) {
			project = new Project();
			project.setName(projectName);
		}
		
		project.addTimesheet(timesheet);
		timesheet.setProject(project);
		employee.addTimesheet(timesheet);
		projectRepo.save(project);
		Timesheet savedTimesheet = timesheetRepo.save(timesheet);
		return new ResponseEntity<Timesheet>(savedTimesheet, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity deleteTimesheet(@PathVariable("id") Long id) {
		if (timesheetRepo.existsById(id)) {
			timesheetRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
