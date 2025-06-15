import { Component, OnInit } from '@angular/core';
import { ToDo } from 'ToDo';
import { TodoService } from '../todo.service';
import { Router } from '@angular/router';
import { LoginserviceService } from '../loginservice.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  tasks: ToDo[] = [];  // Array to hold tasks
  selectedTask: ToDo | null = null;  // For editing a task
  isAddFormVisible = false;  // Toggle visibility for the Add Form
  isEditFormVisible = false;  // Toggle visibility for the Edit Form

  constructor(
    private todoService: TodoService,
    private router: Router,  // Assuming this might be used to navigate
    private loginService: LoginserviceService  // Assuming this handles login-related services
  ) {}

  ngOnInit(): void {
    const user = this.loginService.getLoggedInUser();
    if (!user || !user.id) {
      this.router.navigate(['/login']);
    } else {
      this.fetchTasks(user.id);

    }
  }

  // Method to fetch tasks for the logged-in user
  fetchTasks(userId: number): void {
    this.todoService.getTasksByUserId(userId).subscribe(data => {
      this.tasks = data;
    }, error => {
      console.error('Error fetching tasks', error);
    });
  }
  // Method to show Add task form
  onAddNewTask(): void {
    this.isAddFormVisible = true;
    this.isEditFormVisible = false;
  }

  // Method to show Edit task form
  onEdit(task: ToDo): void {
    this.selectedTask = { ...task };  // Copy the task to selectedTask for editing
    this.isEditFormVisible = true;
    this.isAddFormVisible = false;
  }

  // Method to delete a task
  onDelete(taskId: number): void {
    const user = this.loginService.getLoggedInUser();
    if (user) {
      const userId = user.id;
      this.todoService.deleteTask(userId, taskId).subscribe(() => {
        // Remove the task from the current tasks array
        this.tasks = this.tasks.filter(task => task.id.taskId !== taskId);
      });
    }
  }
  
  

  goToProfile(): void {
    this.router.navigate(['/profile']).then(() => {
      location.reload(); // Force a reload of the page after navigation
    });
  }
  

  // Method called after a task is added
  onTaskAdded(): void {
    this.isAddFormVisible = false;  // Hide the Add form
    const user = this.loginService.getLoggedInUser();
    if (user) {
      this.fetchTasks(user.id);

    }
  }

  // Method called after a task is updated
  onTaskUpdated(): void {
    this.isEditFormVisible = false;  // Hide the Edit form
    const user = this.loginService.getLoggedInUser();
    if (user) {
      this.fetchTasks(user.id);

    }
  }

  // Method to log out the user
  onLogout(): void {
    this.loginService.logout();  // Logout the user (clear user from service)
    this.router.navigate(['/login']);  // Navigate to login page (if necessary)
  }
}

