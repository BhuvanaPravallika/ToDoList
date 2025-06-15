package com.nalajala.todolist.ToDolist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name="todo_tbl")
public class ToDo {
	   @EmbeddedId
	    private ToDoId id;

	    @Column
	    private String taskName;

	    @Column
	    private String taskDescription;

	    @Column
	    private String taskStatus;

	    @ManyToOne
	    @MapsId("userId") // maps userId of the embedded id
	    @JoinColumn(name = "user_id")
	    @JsonIgnore
	    private Login user;

	    public ToDo() {}

	    public ToDo(ToDoId id, String taskName, String taskDescription, String taskStatus, Login user) {
	        this.id = id;
	        this.taskName = taskName;
	        this.taskDescription = taskDescription;
	        this.taskStatus = taskStatus;
	        this.user = user;
	    }

	    public ToDoId getId() {
	        return id;
	    }

	    public void setId(ToDoId id) {
	        this.id = id;
	    }

	    public String getTaskName() {
	        return taskName;
	    }

	    public void setTaskName(String taskName) {
	        this.taskName = taskName;
	    }

	    public String getTaskDescription() {
	        return taskDescription;
	    }

	    public void setTaskDescription(String taskDescription) {
	        this.taskDescription = taskDescription;
	    }

	    public String getTaskStatus() {
	        return taskStatus;
	    }

	    public void setTaskStatus(String taskStatus) {
	        this.taskStatus = taskStatus;
	    }

	    public Login getUser() {
	        return user;
	    }

	    public void setUser(Login user) {
	        this.user = user;
	    }
	    
	   
	}