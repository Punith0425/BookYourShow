import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './userprofile.html',
  styleUrls: ['./userprofile.css']
})
export class UserProfileComponent {
  isEditMode: boolean = false;

  // Added avatar field here 👇
  user = {
    name: 'John Doe',
    email: 'john@example.com',
    phone: '9876543210',
    password: '********',
    avatar: 'https://i.pinimg.com/originals/23/fa/25/23fa259e6faefc4ecd999f179f483150.jpg'
  };

  editableUser = { ...this.user }; // includes avatar now

  toggleEdit() {
    this.isEditMode = !this.isEditMode;
    if (!this.isEditMode) {
      this.editableUser = { ...this.user };
    }
  }

  saveProfile() {
    this.user = { ...this.editableUser };
    this.isEditMode = false;
    alert('✅ Profile updated successfully!');
  }
}
