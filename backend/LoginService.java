package com.nalajala.todolist.ToDolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nalajala.todolist.ToDolist.entity.Login;
import com.nalajala.todolist.ToDolist.iservice.ILoginService;
import com.nalajala.todolist.ToDolist.repository.LoginRepository;

@Service

public class LoginService implements ILoginService{

	@Autowired
    private LoginRepository repo;

    @Override
    public boolean validateLogin(String email, String password) {
        return repo.findByEmailAndPassword(email, password) != null;
    }

    @Override
    public boolean registerUser(String fname, String lname, String email, String password) {
        if (repo.findByEmail(email) != null) return false;
        Login newUser = new Login();
        newUser.setFname(fname);
        newUser.setLname(lname);
        newUser.setEmail(email);
        newUser.setPassword(password);
        repo.save(newUser);
        return true;
    }

  
    
    
    @Override
    public List<Login> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public Login getUserByEmail(String email) {
        return repo.findByEmail(email);
    }
}
