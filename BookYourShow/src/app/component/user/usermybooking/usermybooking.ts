import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-mybooking',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './usermybooking.html',
  styleUrls: ['./usermybooking.css']
})
export class UserMyBookingComponent implements OnInit {
  bookings: any[] = [];

  ngOnInit(): void {
    // Load from localStorage
    const stored = localStorage.getItem('bookings');
    if (stored) {
      this.bookings = JSON.parse(stored);
    }
  }

  cancelBooking(index: number) {
    this.bookings.splice(index, 1);
    localStorage.setItem('bookings', JSON.stringify(this.bookings));
  }
}
