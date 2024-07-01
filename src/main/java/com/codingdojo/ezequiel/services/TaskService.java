package com.codingdojo.ezequiel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.ezequiel.models.Task;
import com.codingdojo.ezequiel.models.User;
import com.codingdojo.ezequiel.repositories.TaskRepo;
import com.codingdojo.ezequiel.repositories.UserRepo;

@Service
public class TaskService {

	@Autowired
	private TaskRepo tr;
	
	@Autowired
	private UserRepo ur;
	
	public Task saveTask(Task newTask){
		return tr.save(newTask);
	}
	
	public User findUser(Long id) {
		return ur.findById(id).orElse(null);
	}
	
	public Task findTask(Long id) {
		return tr.findById(id).orElse(null);
	}
	
	public List<Task> findAllTasks() {
        return tr.findAll();
    }
	
	public void deleteTask(Long id) {
		tr.deleteById(id);
	}
	
	public List<Task> orderAsc(){
		return tr.findByOrderByPriorityAsc();
	}
	
	public List<Task> orderDesc(){
		return tr.findByOrderByPriorityDesc();
	}
}
