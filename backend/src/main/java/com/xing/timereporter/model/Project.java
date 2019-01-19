package com.xing.timereporter.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "project")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique=true)
	private String name;
	@OneToMany(targetEntity = Timesheet.class)
	private List<Timesheet> timesheet;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Timesheet> getTimesheet() {
		return timesheet;
	}

	public void setTimesheet(List<Timesheet> timesheet) {
		this.timesheet = timesheet;
	}

	public Long getId() {
		return id;
	}

	public void addTimesheet(Timesheet timesheet) {
		this.timesheet.add(timesheet);
	}

}
