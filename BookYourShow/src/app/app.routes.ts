import { Routes } from '@angular/router';
import { LoginComponent } from './component/admin/login/login';
import { RegisterComponent } from './component/admin/register/register';
import { UserLoginComponent } from './component/user/userlogin/userlogin';
import { UserRegisterComponent } from './component/user/userregister/userregister';
import { UserHomeComponent } from './component/user/userhome/userhome';
import { UserBookingComponent } from './component/user/userbooking/userbooking';
import { UserPaymentComponent } from './component/user/userpayment/userpayment';
import { UserMyBookingComponent } from './component/user/usermybooking/usermybooking';
import { UserProfileComponent } from './component/user/userprofile/userprofile';
import { AdminDashboardComponent } from './component/admin/admindashboard/admindashboard';
import { AdminMoviesComponent } from './component/admin/admin-movies/admin-movies';
import { AdminTheatersComponent } from './component/admin/admin-theaters/admin-theaters';
import { AdminUsersComponent } from './component/admin/admin-users/admin-users';
import { AdminNavbarComponent } from './component/admin/admin-navbar/admin-navbar';


export const routes: Routes = [
   { path: '', redirectTo: 'user/login', pathMatch: 'full' },
  { path: 'admin/login', component: LoginComponent },
  { path: 'admin/register', component: RegisterComponent },
  { path: 'user/login', component: UserLoginComponent },
  { path: 'user/register', component: UserRegisterComponent },
  { path: 'user/home', component: UserHomeComponent },
  { path: 'user/booking', component: UserBookingComponent },
  { path: 'user/payment', component: UserPaymentComponent },
  { path: 'user/mybookings', component: UserMyBookingComponent },
  { path: 'user/profile', component: UserProfileComponent },
  { path: 'admin/dashboard', component: AdminDashboardComponent },
  { path: 'admin/movies', component: AdminMoviesComponent },
  { path: 'admin/theaters', component: AdminTheatersComponent },
  { path: 'admin/users', component: AdminUsersComponent },
  { path: 'admin/navbar', component: AdminNavbarComponent }
];
