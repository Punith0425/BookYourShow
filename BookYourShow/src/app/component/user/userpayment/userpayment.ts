import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './userpayment.html',
  styleUrls: ['./userpayment.css']
})
export class UserPaymentComponent implements OnInit {
  movie: any = {};
  selectedDate: string = '';
  selectedTime: string = '';
  selectedSeats: string[] = [];
  selectedTheater: string = '';

  paymentMethod: string = '';
  upiId: string = '';
  cardNumber: string = '';
  expiry: string = '';
  cvv: string = '';
  amount: number = 0;

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
  this.route.queryParams.subscribe(params => {
    this.movie = {
      name: params['name'],
      description: params['description'],
      duration: params['duration'],
      image: params['image'],
    };
    this.selectedDate = params['date'];
    this.selectedTime = params['time'];
    this.selectedSeats = params['seats'] ? params['seats'].split(',') : [];
    this.selectedTheater = params['theater'];
    this.amount = params['amount'] ? Number(params['amount']) : 0;
  });
}


  payNow() {
    if (!this.paymentMethod) {
      alert('⚠️ Please select a payment method.');
      return;
    }

    if (this.paymentMethod === 'upi') {
      if (!this.upiId) {
        alert('⚠️ Please enter your UPI ID.');
        return;
      }
      alert(`✅ Redirecting to Google Pay / PhonePe for UPI ID: ${this.upiId}`);
      window.open('https://pay.google.com', '_blank');
    }

    if (this.paymentMethod === 'card') {
      if (!this.cardNumber || !this.expiry || !this.cvv) {
        alert('⚠️ Please fill all card details.');
        return;
      }
      alert(`✅ Payment successful using Card ending with ${this.cardNumber.slice(-4)}`);
    }

    // After payment success, go to a confirmation page or home
    this.router.navigate(['']);
  }
}
