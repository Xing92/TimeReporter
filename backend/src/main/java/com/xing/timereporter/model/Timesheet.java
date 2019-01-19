package com.xing.timereporter.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "timesheet")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Timesheet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long id_employee;
	private Long id_project;
//	@NotNull
	@ManyToOne(targetEntity = Employee.class)
	private Employee employee;
//	@NotNull
	@ManyToOne(targetEntity = Project.class)
	private Project project;
//	@NotNull
	private LocalDateTime timeStart = LocalDateTime.now();
//	@NotNull
	private LocalDateTime timeEnd = LocalDateTime.now();

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public LocalDateTime getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(LocalDateTime timeStart) {
		this.timeStart = timeStart;
	}

	public LocalDateTime getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(LocalDateTime timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Long getId_employee() {
		return id_employee;
	}

	public void setId_employee(Long id_employee) {
		this.id_employee = id_employee;
	}

	public Long getId_project() {
		return id_project;
	}

	public void setId_project(Long id_project) {
		this.id_project = id_project;
	}

	public Long getId() {
		return id;
	}

}
