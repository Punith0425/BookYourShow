import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminNavbarComponent as AdminNavbarComponent } from "../admin-navbar/admin-navbar";

@Component({
  selector: 'app-admin-users',
  standalone: true,
  imports: [CommonModule, AdminNavbarComponent],
  templateUrl: './admin-users.html',
  styleUrls: ['./admin-users.css']
})
export class AdminUsersComponent {
  users = [
    { id: 1, name: 'Krish', email: 'krish@example.com' },
    { id: 2, name: 'John', email: 'john@example.com' },
    { id: 3, name: 'Priya', email: 'priya@example.com' }
  ];

  deleteUser(id: number) {
    this.users = this.users.filter(u => u.id !== id);
  }
}
