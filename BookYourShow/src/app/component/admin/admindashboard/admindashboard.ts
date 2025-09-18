import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgChartsModule } from 'ng2-charts';
import { ChartConfiguration, ChartOptions } from 'chart.js';
import { AdminNavbarComponent as AdminNavbarComponent } from "../admin-navbar/admin-navbar";

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, NgChartsModule, AdminNavbarComponent, AdminNavbarComponent],
  templateUrl: './admindashboard.html',
  styleUrls: ['./admindashboard.css']
})

export class AdminDashboardComponent {
  // 📊 Bar Chart for Monthly Bookings
  barChartOptions: ChartOptions<'bar'> = {
    responsive: true,
    plugins: { legend: { display: true, position: 'top' } }
  };
  barChartLabels = ['Jan', 'Feb', 'Mar', 'Apr', 'May'];
  barChartData: ChartConfiguration<'bar'>['data']['datasets'] = [
    { data: [15, 25, 35, 20, 40], label: 'Bookings' }
  ];

  // 🥧 Pie Chart for System Overview
  pieChartOptions: ChartOptions<'pie'> = {
    responsive: true,
    plugins: { legend: { position: 'right' } }
  };
  pieChartLabels = ['Movies', 'Theaters', 'Users'];
  pieChartData = [300, 500, 200];
}
