package com.nalajala.todolist.ToDolist.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ToDoId implements Serializable{
	
	  private int taskId;
	    private int userId;

	    public ToDoId() {}

	    public ToDoId(int taskId, int userId) {
	        this.taskId = taskId;
	        this.userId = userId;
	    }

	    public int getTaskId() {
	        return taskId;
	    }

	    public void setTaskId(int taskId) {
	        this.taskId = taskId;
	    }

	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof ToDoId)) return false;
	        ToDoId that = (ToDoId) o;
	        return taskId == that.taskId && userId == that.userId;
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(taskId, userId);
	    }

}
