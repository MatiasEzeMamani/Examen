package com.codingdojo.ezequiel.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.ezequiel.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
	
	User findByEmail(String email);
	
	List<User> findAll();
}