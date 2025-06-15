package com.nalajala.todolist.ToDolist.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user_tbl")
public class Login {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment in MySQL
	private int id;
	
	@Column(name="FirstName")
	private String fname;
	
	@Column(name="LastName")
	private String lname;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Address")
	private String address;  // New field for address
	
	@Column(name="PhoneNumber")
	private String phoneNumber;  // New field for phone number
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<ToDo> todos = new ArrayList<>();

	public Login() {
		super();
	}

	public Login(int id, String fname, String lname, String email, String password, String address, String phoneNumber, List<ToDo> todos) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.todos = todos;
	}

	// Getters and Setters for all fields
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<ToDo> getTodos() {
		return todos;
	}

	public void setTodos(List<ToDo> todos) {
		this.todos = todos;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", password=" + password 
		       + ", address=" + address + ", phoneNumber=" + phoneNumber + ", todos=" + todos + "]";
	}
}
