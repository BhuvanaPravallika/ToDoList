package com.nalajala.todolist.ToDolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nalajala.todolist.ToDolist.entity.Login;
import com.nalajala.todolist.ToDolist.entity.ToDo;
import com.nalajala.todolist.ToDolist.entity.ToDoId;
import com.nalajala.todolist.ToDolist.iservice.IToDoService;
import com.nalajala.todolist.ToDolist.repository.LoginRepository;
import com.nalajala.todolist.ToDolist.repository.ToDoRepository;

@Service
public class ToDoService implements IToDoService {

	 @Autowired
	    private ToDoRepository toDoRepo;

	    @Autowired
	    private LoginRepository loginRepo;

	    @Override
	    public ToDo addTask(ToDo task, int userId) {
	        Login user = loginRepo.findById(userId).orElse(null);
	        if (user != null) {
	            // Get max taskId for this user
	            List<ToDo> userTasks = toDoRepo.findByUserId(userId);
	            int newTaskId = userTasks.stream()
	                                     .mapToInt(t -> t.getId().getTaskId())
	                                     .max()
	                                     .orElse(0) + 1;

	            ToDoId newId = new ToDoId(newTaskId, userId);
	            task.setId(newId);
	            task.setUser(user);
	            return toDoRepo.save(task);
	        }
	        return null;
	    }

	    @Override
	    public List<ToDo> getTasksByUserId(int userId) {
	        return toDoRepo.findByUserId(userId);
	    }

	    @Override
	    public ToDo getTaskById(ToDoId id) {
	        return toDoRepo.findById(id).orElse(null);
	    }

	    @Override
	    public ToDo updateTask(ToDoId id, ToDo updatedTask) {
	        Optional<ToDo> existingTask = toDoRepo.findById(id);
	        if (existingTask.isPresent()) {
	            ToDo task = existingTask.get();
	            task.setTaskName(updatedTask.getTaskName());
	            task.setTaskDescription(updatedTask.getTaskDescription());
	            task.setTaskStatus(updatedTask.getTaskStatus());
	            return toDoRepo.save(task);
	        }
	        return null;
	    }

	    @Override
	    public void deleteTask(ToDoId id) {
	        toDoRepo.deleteById(id);
	    }
}
