import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminNavbarComponent } from '../admin-navbar/admin-navbar';

@Component({
  selector: 'app-admin-movies',
  standalone: true,
  imports: [CommonModule, FormsModule, AdminNavbarComponent],
  templateUrl: './admin-movies.html',
  styleUrls: ['./admin-movies.css']
})
export class AdminMoviesComponent {
  search: string = '';
  editingId: number | null = null;

  movies = [
    { 
      id: 1, 
      name: 'Inception', 
      duration: '2h 28m', 
      genre: 'Sci-Fi',
      description: 'A mind-bending thriller by Christopher Nolan.',
      image: 'https://www.originalfilmart.com/cdn/shop/files/inception_2010_french_original_film_art_a_2000x.webp?v=1686692703'
    },
    { 
      id: 2, 
      name: 'Interstellar', 
      duration: '2h 49m', 
      genre: 'Adventure',
      description: 'A space journey to save humanity.',
      image: 'https://image.tmdb.org/t/p/w220_and_h330_face/rUFkW8eGSsckq1y9cYeYevqh4lO.jpg'
    },
    { 
      id: 3, 
      name: 'Avengers', 
      duration: '2h 24m', 
      genre: 'Action',
      description: 'Earth’s mightiest heroes unite.',
      image: 'https://m.media-amazon.com/images/I/81ExhpBEbHL._AC_SY679_.jpg'
    }
  ];

  newMovie = { name: '', duration: '', genre: '', description: '', image: '' };

  addMovie() {
    if (!this.newMovie.name || !this.newMovie.duration || !this.newMovie.genre || !this.newMovie.description || !this.newMovie.image) {
      alert('⚠️ Please fill all fields!');
      return;
    }
    this.movies.push({ id: this.movies.length + 1, ...this.newMovie });
    this.newMovie = { name: '', duration: '', genre: '', description: '', image: '' };
  }

  deleteMovie(id: number) {
    this.movies = this.movies.filter(m => m.id !== id);
  }
}
