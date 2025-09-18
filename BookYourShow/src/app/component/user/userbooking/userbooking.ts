import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-booking',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './userbooking.html',
  styleUrls: ['./userbooking.css']
})
export class UserBookingComponent implements OnInit {
  movie: any = {};

  dates: string[] = ['12 Sept 2025', '13 Sept 2025', '14 Sept 2025'];
  times: string[] = ['10:00 AM', '01:00 PM', '04:00 PM', '07:00 PM'];
  theaters: string[] = ['PVR Cinemas', 'INOX', 'Cinepolis', 'Carnival Cinemas'];

  selectedDate: string = '';
  selectedTime: string = '';
  selectedTheater: string = '';
  selectedSeats: string[] = [];

  seats = [
    { id: 'A1', status: 'available' },
    { id: 'A2', status: 'available' },
    { id: 'A3', status: 'booked' },
    { id: 'A4', status: 'available' },
    { id: 'A5', status: 'available' },
    { id: 'B1', status: 'available' },
    { id: 'B2', status: 'booked' },
    { id: 'B3', status: 'available' },
    { id: 'B4', status: 'available' },
    { id: 'B5', status: 'available' },
    { id: 'C1', status: 'available' },
    { id: 'C2', status: 'available' },
    { id: 'C3', status: 'booked' },
    { id: 'C4', status: 'available' },
    { id: 'C5', status: 'available' },
    { id: 'D1', status: 'available' },
    { id: 'D2', status: 'booked' },
    { id: 'D3', status: 'available' },
    { id: 'D4', status: 'available' },
    { id: 'D5', status: 'available' }
  ];
//selectedTheater: any;
//theaters: any;

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.movie = {
        name: params['name'],
        description: params['description'],
        duration: params['duration'],
        image: params['image']
      };
    });
  }

  toggleSeat(seat: any) {
    if (seat.status === 'booked') return;

    if (this.selectedSeats.includes(seat.id)) {
      this.selectedSeats = this.selectedSeats.filter(s => s !== seat.id);
      seat.status = 'available';
    } else {
      this.selectedSeats.push(seat.id);
      seat.status = 'selected';
    }
  }

  confirmBooking() {
    if (!this.selectedDate || !this.selectedTime || this.selectedSeats.length === 0) {
      alert('⚠️ Please select date, time, and at least one seat.');
      return;
    }

    const amount = this.selectedSeats.length * 150; // Example: ₹150 per seat

    this.router.navigate(['user/payment'], {
      queryParams: {
        name: this.movie.name,
        description: this.movie.description,
        duration: this.movie.duration,
        image: this.movie.image,
        date: this.selectedDate,
        time: this.selectedTime,
        seats: this.selectedSeats.join(','),
        amount: amount
      }
    });
  }
}
