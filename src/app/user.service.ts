import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './register/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}

  getUserById(userId: number): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/user/profile/${userId}`);
  }
  
  
}
