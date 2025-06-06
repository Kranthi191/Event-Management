package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	User findByEmail(String email);
	User findByName(String name);
	User findById(long id);
	//void deleteByEventId(Long eventId);

}
