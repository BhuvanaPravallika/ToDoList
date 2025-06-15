import { Component } from '@angular/core';
import { RegisterserviceService } from '../registerservice.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { LoginserviceService } from '../loginservice.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  user = { fname: '', lname: '', email: '', password: '' };

  constructor(private loginService: LoginserviceService, private router: Router) {}

  onSubmit(form: NgForm) {
    // Call the service to register the user
    this.loginService.register(this.user).subscribe(
      (response) => {
        // Log the response and alert user if registration is successful
        console.log(response);
        alert('Registration Successful!');

        // Reset the form fields and validation errors
        form.resetForm();  // Reset the form fields
        this.user = { fname: '', lname: '', email: '', password: '' }; // Clear the user object
      },
      (error) => {
        // Handle error case
        console.log(error);
        alert('Error in registration');
      }
    );
  }
}