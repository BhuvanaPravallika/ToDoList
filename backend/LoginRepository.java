package com.nalajala.todolist.ToDolist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nalajala.todolist.ToDolist.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login,Integer>{
	
	 Login findByEmailAndPassword(String email, String password);
	    Login findByEmail(String email);
	    Optional<Login> findById(Integer id);



}
