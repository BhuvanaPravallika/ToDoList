package com.nalajala.todolist.ToDolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nalajala.todolist.ToDolist.entity.ToDo;
import com.nalajala.todolist.ToDolist.entity.ToDoId;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, ToDoId>{
	 List<ToDo> findByUserId(int userId);


}
