import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterserviceService {
  private apiUrl = 'http://localhost:8080/user/register';  // Your API URL

  constructor(private http: HttpClient) {}

  // Modify the method to expect a text response
  registerUser(email: string, password: string): Observable<string> {
    return this.http.post(this.apiUrl, { email, password }, { responseType: 'text' });
  }
}
