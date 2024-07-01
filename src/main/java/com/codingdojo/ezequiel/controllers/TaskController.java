package com.codingdojo.ezequiel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.ezequiel.models.Priority;
import com.codingdojo.ezequiel.models.Task;
import com.codingdojo.ezequiel.models.User;
import com.codingdojo.ezequiel.services.TaskService;
import com.codingdojo.ezequiel.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class TaskController {
	@Autowired 
	private UserService serv;
	
	@Autowired
	private TaskService ts;
	
	@GetMapping("/tasks")
	public String tasks(HttpSession session, Model model) {
		
		/*REVISION DE SESION*/
		User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null
		if(userTemp == null) {
			return "redirect:/";
		}
		/*REVISION DE SESION*/
		

	    // Obtener todas las tareas
	    List<Task> allTasks = ts.findAllTasks(); // Necesitarás un método findAllTasks en el servicio TaskService

	    // Añadir las tareas al modelo
	    model.addAttribute("tasks", allTasks);
		
		return "tasks.jsp";
	}
	
	@GetMapping("/tasks/new")
	public String newTasks(HttpSession session,
						   @ModelAttribute("task") Task task,
						   Model model) {
		/*REVISION DE SESION*/
		User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null
		if(userTemp == null) {
			return "redirect:/";
		}
		/*REVISION DE SESION*/
		
		User myUser = serv.findUser( userTemp.getId() );
		model.addAttribute("user", myUser);
		
		List<User> users = serv.allUsers();
		model.addAttribute("users", users);
		
		model.addAttribute("priority", Priority.Priority);
		
		return "newTask.jsp";
	}
	
	@PostMapping("/create_task")
	public String createTask(@Valid @ModelAttribute("task") Task task,
						 	 BindingResult result,
							 HttpSession session,
							 Model model) {
		/* === REVISAMOS SESION === */
		User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null
		if(userTemp == null) {
			return "redirect:/";
		}
		/* === REVISAMOS SESION === */
		
		if(result.hasErrors()) {
	        User myUser = serv.findUser( userTemp.getId() );
	        model.addAttribute("user", myUser);
	        
	        List<User> users = serv.allUsers();
	        model.addAttribute("users", users);

	        model.addAttribute("priority", Priority.Priority);
	        return "newTask.jsp";
	    } else {
	        task.setCreator(userTemp);
	        ts.saveTask(task);
	        return "redirect:/tasks";
	    }
	}
	
	@GetMapping("/tasks/{id}")
	public String info(@PathVariable("id") Long id,
					   HttpSession session,
					   Model model) {
		/*REVISION DE SESION*/
		User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null
		if(userTemp == null) {
			return "redirect:/";
		}
		/*REVISION DE SESION*/
		
		Task taskInfo = ts.findTask(id);
		model.addAttribute("task", taskInfo);
		return "info.jsp";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,
					   @ModelAttribute("task") Task task,
					   HttpSession session,
					   Model model) {
		
		/*REVISION DE SESION*/
		User userTemp = (User) session.getAttribute("userInSession"); 
		if(userTemp == null) {
			return "redirect:/";
		}
		/*REVISION DE SESION*/
		User myUser = serv.findUser( userTemp.getId() );
        model.addAttribute("user", myUser);
        
        List<User> users = serv.allUsers();
        model.addAttribute("users", users);

        model.addAttribute("priority", Priority.Priority);
		
		
		Task taskEdit = ts.findTask(id);
		if(userTemp.getId() != taskEdit.getCreator().getId()) {
			return "redirect:/";
		}
		
		model.addAttribute("task", taskEdit);
		return "edit.jsp";
	}
	
	@PutMapping("/task/update")
	public String taskUpdate(HttpSession session,
			                 @Valid @ModelAttribute("task") Task task, 
			                 BindingResult result,
			                 Model model) {
		/*REVISION DE SESION*/
		User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null
		if(userTemp == null) {
			return "redirect:/";
		}
		/*REVISION DE SESION*/
		
		if(result.hasErrors()) {
			User myUser = serv.findUser( userTemp.getId() );
	        model.addAttribute("user", myUser);
	        
	        List<User> users = serv.allUsers();
	        model.addAttribute("users", users);

	        model.addAttribute("priority", Priority.Priority);
			return "edit.jsp";
		} else {
			ts.saveTask(task);
			return "redirect:/tasks";
		}
	}
	
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		ts.deleteTask(id);
		return "redirect:/tasks";
	}
	
	@GetMapping("/priority-high-low")
    public String orderAsc(HttpSession session, Model model) {
		
		/*REVISION DE SESION*/
		User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null
		if(userTemp == null) {
			return "redirect:/";
		}
		/*REVISION DE SESION*/
		
        List<Task> tasks = ts.orderAsc();
        model.addAttribute("tasks", tasks);
        return "tasks.jsp";
    }
	
	
	@GetMapping("/priority-low-high")
    public String orderDesc(HttpSession session, Model model) {
		
		/*REVISION DE SESION*/
		User userTemp = (User) session.getAttribute("userInSession"); //Obj User o null
		if(userTemp == null) {
			return "redirect:/";
		}
		/*REVISION DE SESION*/
		
        List<Task> tasks = ts.orderDesc();
        model.addAttribute("tasks", tasks);
        return "tasks.jsp";
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

