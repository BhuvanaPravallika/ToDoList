export interface ToDo {
  id: {
    taskId: number;
    userId: number;
  };
  taskName: string;
  taskDescription: string;
  taskStatus: string;
}
