import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminNavbarComponent as AdminNavbarComponent } from "../admin-navbar/admin-navbar";

@Component({
  selector: 'app-admin-theaters',
  standalone: true,
  imports: [CommonModule, FormsModule, AdminNavbarComponent],
  templateUrl: './admin-theaters.html',
  styleUrls: ['./admin-theaters.css']
})
export class AdminTheatersComponent {
  theaters = [
    { id: 1, name: 'PVR Cinemas', location: 'Bangalore' },
    { id: 2, name: 'INOX', location: 'Chennai' },
    { id: 3, name: 'Cinepolis', location: 'Delhi' }
  ];

  newTheater = { name: '', location: '' };

  addTheater() {
    if (!this.newTheater.name || !this.newTheater.location) {
      alert('Please fill all fields!');
      return;
    }
    this.theaters.push({ id: this.theaters.length + 1, ...this.newTheater });
    this.newTheater = { name: '', location: '' };
  }

  deleteTheater(id: number) {
    this.theaters = this.theaters.filter(t => t.id !== id);
  }
}
