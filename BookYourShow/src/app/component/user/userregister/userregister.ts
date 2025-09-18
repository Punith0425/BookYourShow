import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-userregister',
  templateUrl: './userregister.html',
  styleUrls: ['./userregister.css'],
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule]
})
export class UserRegisterComponent {
  registerForm: FormGroup;
  submitted = false;

  constructor(private fb: FormBuilder, private router: Router) {
    this.registerForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]]
    });
  }

  get f() {
    return this.registerForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    if (this.registerForm.invalid) {
      return;
    }

    if (this.registerForm.value.password !== this.registerForm.value.confirmPassword) {
      alert('❌ Passwords do not match!');
      return;
    }

    // Simulate successful registration
    alert('✅ User Registration Successful!');
    this.router.navigate(['/user/login']); // 👈 redirects to user login page
  }
}
