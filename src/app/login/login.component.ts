import { Component } from '@angular/core';
import { LoginserviceService } from '../loginservice.service';
import { Route, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  user = {
    email: '',
    password: ''
  };
  formSubmitted = false;
  errorMessage: string = '';

  constructor(private loginService: LoginserviceService, private router: Router) {}

  onSubmit(form: NgForm) {
    this.formSubmitted = true;
    this.errorMessage = '';

    if (form.valid) {
      this.loginService.login(this.user.email, this.user.password).subscribe({
        next: (response) => {
          console.log('Login response:', response);  // 🧪 Check backend response

          // ✅ Try to extract user ID based on backend structure
          const userId = response?.id || response?.user?.id;

          if (userId) {
            sessionStorage.setItem('userId', userId.toString());
            console.log('Saved userId to sessionStorage:', sessionStorage.getItem('userId'));
            this.router.navigate(['/home']);
          } else {
            this.errorMessage = 'Login successful but user ID is missing.';
            console.error('User ID is missing from login response.');
          }
        },
        error: (err) => {
          console.error('Login error:', err);
          this.errorMessage = 'Invalid credentials or server error.';
        }
      });
    }
  }
  
  
}