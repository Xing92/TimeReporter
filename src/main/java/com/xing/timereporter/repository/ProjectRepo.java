package com.xing.timereporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xing.timereporter.model.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {

}
