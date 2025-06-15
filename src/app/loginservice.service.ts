import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginserviceService {

  private baseUrl = 'http://localhost:8080/user';
  private currentUser: any = null;

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    const body = { email, password };
    return this.http.post<any>(`${this.baseUrl}/login`, body).pipe(
      tap((user) => {
        if (user && user.email) {
          this.currentUser = user;
          localStorage.setItem('loggedInUser', JSON.stringify(user));
        }
      })
    );
  }
  getLoggedInUser(): any {
    if (!this.currentUser) {
      const userJson = localStorage.getItem('loggedInUser');
      if (userJson) {
        this.currentUser = JSON.parse(userJson);
      }
    }
    return this.currentUser;
  }
  getUserProfile(userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/profile/${userId}`);
  }

  updateUserProfile(userId: number, updatedProfile: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/updateProfile/${userId}`, updatedProfile);
  }

  logout(): void {
    this.currentUser = null;
    localStorage.removeItem('loggedInUser');
  }

  register(userData: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register`, userData);
  }

}
