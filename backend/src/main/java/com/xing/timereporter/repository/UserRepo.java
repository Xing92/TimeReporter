package com.xing.timereporter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xing.timereporter.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	public User findByEmail(String email);

}
