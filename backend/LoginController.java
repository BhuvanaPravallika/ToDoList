package com.nalajala.todolist.ToDolist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nalajala.todolist.ToDolist.entity.Login;
import com.nalajala.todolist.ToDolist.entity.ToDo;
import com.nalajala.todolist.ToDolist.iservice.ILoginService;
import com.nalajala.todolist.ToDolist.repository.LoginRepository;
import com.nalajala.todolist.ToDolist.service.ToDoService;
import org.springframework.http.HttpStatus;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class LoginController {

	 @Autowired
	    private ILoginService loginService;
	 
	 @Autowired
	    private LoginRepository loginRepository;

	    @GetMapping("/profile/{id}")
	    public ResponseEntity<Login> getUserProfile(@PathVariable Integer id) {
	        Optional<Login> optionalUser = loginRepository.findById(id);

	        if (optionalUser.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }

	        Login user = optionalUser.get();
	        user.setPassword(null); // Hide password for security

	        return ResponseEntity.ok(user);
	    }

	    @PostMapping("/login")
	    public Login login(@RequestBody Login login) {
	        boolean isValid = loginService.validateLogin(login.getEmail(), login.getPassword());
	        return isValid ? loginService.getUserByEmail(login.getEmail()) : null;
	    }

	    @PostMapping("/register")
	    public Map<String, String> register(@RequestBody Login login) {
	        boolean isRegistered = loginService.registerUser(login.getFname(), login.getLname(), login.getEmail(), login.getPassword());
	        Map<String, String> response = new HashMap<>();
	        
	        if (isRegistered) {
	            response.put("message", "Registration successful!");
	        } else {
	            response.put("message", "User already exists");
	        }
	        
	        return response; // Return response as JSON
	    }

	    @GetMapping("/all")
	    public List<Login> getAllUsers() {
	        return loginService.getAllUsers();
	    }
	    
	    
	    @PutMapping("/updateProfile/{id}")
	    public ResponseEntity<Login> updateUserProfile(@PathVariable Integer id, @RequestBody Login updatedUser) {
	        Optional<Login> optionalUser = loginRepository.findById(id);

	        if (optionalUser.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }

	        Login existingUser = optionalUser.get();

	        // Update fields (ensure not null or empty values)
	        existingUser.setFname(updatedUser.getFname() != null ? updatedUser.getFname() : existingUser.getFname());
	        existingUser.setLname(updatedUser.getLname() != null ? updatedUser.getLname() : existingUser.getLname());
	        existingUser.setEmail(updatedUser.getEmail() != null ? updatedUser.getEmail() : existingUser.getEmail());

	        // Update phone number and address if provided
	        existingUser.setPhoneNumber(updatedUser.getPhoneNumber() != null ? updatedUser.getPhoneNumber() : existingUser.getPhoneNumber());
	        existingUser.setAddress(updatedUser.getAddress() != null ? updatedUser.getAddress() : existingUser.getAddress());

	        // If password is provided, update it
	        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
	            existingUser.setPassword(updatedUser.getPassword());
	        }

	        // Save the updated user
	        Login savedUser = loginRepository.save(existingUser);

	        return ResponseEntity.ok(savedUser); // Return updated user
	    }

	    
	  
}
