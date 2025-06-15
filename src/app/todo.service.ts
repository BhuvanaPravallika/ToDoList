import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ToDo } from 'ToDo';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  // Define the base URL of your backend API
  private apiUrl = 'http://localhost:8080/todo'; 

  constructor(private http: HttpClient) { }

  // Update a task for a specific user and task ID
  updateTask(userId: number, taskId: number, task: ToDo): Observable<ToDo> {
    // Make the PUT request to update the task
    return this.http.put<ToDo>(`${this.apiUrl}/update/${userId}/${taskId}`, task);
  }
  

  addTask(userId: number, task: ToDo): Observable<ToDo> {
    return this.http.post<ToDo>(`${this.apiUrl}/add/${userId}`, task);
  }

  // Delete a task by userId and taskId
  deleteTask(userId: number, taskId: number): Observable<any> {
    return this.http.delete(`http://localhost:8080/todo/delete/${userId}/${taskId}`, { responseType: 'text' });
  }
  
  
  getTasksByUserId(userId: number): Observable<ToDo[]> {
    return this.http.get<ToDo[]>(`http://localhost:8080/todo/user/${userId}`);
  }
  
  getTasks(userId: number): Observable<ToDo[]> {
    return this.http.get<ToDo[]>(`http://localhost:8080/api/todo/${userId}`);
  }
  

}