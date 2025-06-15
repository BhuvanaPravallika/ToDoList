package com.nalajala.todolist.ToDolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nalajala.todolist.ToDolist.entity.ToDo;
import com.nalajala.todolist.ToDolist.entity.ToDoId;
import com.nalajala.todolist.ToDolist.iservice.IToDoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/todo")
public class ToDoController {
	 @Autowired
	    private IToDoService toDoService;

	    @PostMapping("/add/{userId}")
	    public ToDo addTask(@PathVariable int userId, @RequestBody ToDo task) {
	        return toDoService.addTask(task, userId);
	    }

	    @GetMapping("/user/{userId}")
	    public List<ToDo> getUserTasks(@PathVariable int userId) {
	        return toDoService.getTasksByUserId(userId);
	    }

	    @GetMapping("/{userId}/{taskId}")
	    public ToDo getTaskById(@PathVariable int userId, @PathVariable int taskId) {
	        return toDoService.getTaskById(new ToDoId(taskId, userId));
	    }

	    @PutMapping("/update/{userId}/{taskId}")
	    public ToDo updateTask(@PathVariable int userId,
	                           @PathVariable int taskId,
	                           @RequestBody ToDo updatedTask) {
	        return toDoService.updateTask(new ToDoId(taskId, userId), updatedTask);
	    }

	    @DeleteMapping("/delete/{userId}/{taskId}")
	    public ResponseEntity<Void> deleteTask(@PathVariable int userId, @PathVariable int taskId) {
	        toDoService.deleteTask(new ToDoId(taskId, userId));
	        return ResponseEntity.noContent().build(); // ✅ returns 204 No Content
	    }

	    
}
