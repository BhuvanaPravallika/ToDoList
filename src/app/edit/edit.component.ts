import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ToDo } from 'ToDo';
import { TodoService } from '../todo.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent {
  @Input() taskToEdit!: ToDo;
  @Output() taskUpdated = new EventEmitter<void>();

  constructor(private todoService: TodoService) {}

  onSubmit(): void {
    const taskId = this.taskToEdit.id.taskId;
    const userId = this.taskToEdit.id.userId;
  
    if (taskId !== undefined && userId !== undefined) {
      this.todoService.updateTask(userId, taskId, this.taskToEdit).subscribe(() => {
        this.taskUpdated.emit();
      });
    } else {
      console.error('Invalid task ID or user ID:', this.taskToEdit);
    }
  }
  
}
