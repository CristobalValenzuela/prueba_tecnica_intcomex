package com.incomex.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incomex.api.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByName(String name);
}
