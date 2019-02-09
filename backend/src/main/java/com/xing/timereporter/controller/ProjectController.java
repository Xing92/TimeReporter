package com.xing.timereporter.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xing.timereporter.model.Project;
import com.xing.timereporter.repository.ProjectRepo;

@RestController
@RequestMapping("/api/project")
public class ProjectController {


	private final ProjectRepo projectRepo;

	public ProjectController(@Autowired ProjectRepo projectRepo) {
		this.projectRepo = projectRepo;
	}

	@RequestMapping(method = RequestMethod.GET, value = "all")
	public ResponseEntity<List<Project>> getAllProjects() {
		List<Project> projects = projectRepo.findAll();
		if (projects.isEmpty()) {
			return new ResponseEntity<List<Project>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="{id}")
	public ResponseEntity<Optional<Project>> getProjectById(@PathVariable("id") Long id) {
		Optional<Project> project = projectRepo.findById(id);
		if (project == null) {
			return new ResponseEntity<Optional<Project>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Optional<Project>>(project, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Project> addProject(Project project) {
		Project savedProject = projectRepo.save(project);
		return new ResponseEntity<Project>(savedProject, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value ="simple")
	public ResponseEntity<Project> addSimpleProject(@RequestParam String projectName) {
		Project project = new Project();
		project.setName(projectName);
		Project savedProject = projectRepo.save(project);
		return new ResponseEntity<Project>(savedProject, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value="{id}")
	public ResponseEntity deleteProject(@PathVariable("id") Long id) {
		if (projectRepo.existsById(id)) {
			projectRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
