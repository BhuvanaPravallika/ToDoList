package com.nalajala.todolist.ToDolist.iservice;

import java.util.List;

import com.nalajala.todolist.ToDolist.entity.ToDo;
import com.nalajala.todolist.ToDolist.entity.ToDoId;

public interface IToDoService {

	 
	 ToDo addTask(ToDo task, int userId);
	    List<ToDo> getTasksByUserId(int userId);
	    ToDo getTaskById(ToDoId id);
	    ToDo updateTask(ToDoId id, ToDo updatedTask);
	    void deleteTask(ToDoId id);
}
