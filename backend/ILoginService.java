package com.nalajala.todolist.ToDolist.iservice;

import java.util.List;

import com.nalajala.todolist.ToDolist.entity.Login;

public interface ILoginService {
	 boolean validateLogin(String email, String password);
	    boolean registerUser(String fname, String lname, String email, String password);
	    List<Login> getAllUsers();
	    Login getUserByEmail(String email);
	    
	 

}
