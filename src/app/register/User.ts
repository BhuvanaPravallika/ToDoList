import { ToDo } from "ToDo";

export interface User {
  id: number;
  fname: string;
  lname: string;
  email: string;
  password: string | null;  // It can be null if you are not exposing password in the frontend
  address: string;
  phoneNumber: string;
  todos: ToDo[];  // Assuming ToDo is another interface for the user's tasks
}