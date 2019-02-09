package com.xing.timereporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xing.timereporter.model.Project;
import com.xing.timereporter.model.User;

public interface ProjectRepo extends JpaRepository<Project, Long> {

	public Project findByName(String name);
}
