import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../register/User';
import { Router } from '@angular/router';
import { LoginserviceService } from '../loginservice.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  userProfile: any;
  userId: number;
  isEditMode: boolean = false;

  constructor(private loginService: LoginserviceService, private router: Router) {}

  ngOnInit(): void {
    const loggedInUser = this.loginService.getLoggedInUser();

    if (!loggedInUser) {
      this.router.navigate(['/login']);
      return;
    }

    this.userId = loggedInUser.id;
    this.loadUserProfile();
  }

  loadUserProfile(): void {
    this.loginService.getUserProfile(this.userId).subscribe(
      data => {
        this.userProfile = data;
      },
      error => {
        console.error('Error loading user profile', error);
      }
    );
  }

  onEdit(): void {
    this.isEditMode = true;
  }

  updateProfile(): void {
    this.loginService.updateUserProfile(this.userId, this.userProfile).subscribe(
      data => {
        this.userProfile = data;
        this.isEditMode = false;
        alert('Profile updated successfully!');
      },
      error => {
        console.error('Error updating profile', error);
      }
    );
  }

  cancelEdit(): void {
    this.isEditMode = false;
    this.loadUserProfile(); // Re-fetch original data
  }
  // Method to navigate back to the home page
  goHome(): void {
    this.router.navigate(['/home']);  // Adjust this route if needed
  }
}
