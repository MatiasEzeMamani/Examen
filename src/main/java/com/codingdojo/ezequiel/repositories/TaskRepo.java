package com.codingdojo.ezequiel.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.ezequiel.models.Task;

public interface TaskRepo extends CrudRepository<Task, Long> {
	List<Task> findAll();
	
	List<Task> findByOrderByPriorityAsc();
    List<Task> findByOrderByPriorityDesc();
}
