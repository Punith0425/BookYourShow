import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-home',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './userhome.html',
  styleUrls: ['./userhome.css']
})
export class UserHomeComponent {
  searchQuery: string = '';

  availableMovies = [
    {
      id: 1,
      name: 'Lokah',
      description: 'Chandra is a 2025 Malayalam superhero film...',
      duration: '2h 28m',
      image: 'https://m.media-amazon.com/images/M/MV5BZThiYjY3ODUtOGM4Yy00OGE0LTliM2UtYjFlNDY0Mzg3OWJlXkEyXkFqcGc@.jpg'
    },
    {
      id: 2,
      name: 'Su From So',
      description: 'Satirical comedy drama with superstition...',
      duration: '2h 17m',
      image: 'https://upload.wikimedia.org/wikipedia/en/a/a3/Su_From_So.jpg'
    },
    {
      id: 3,
      name: 'Demon Slayer: Infinity Castle',
      description: 'The epic finale of Demon Slayer saga.',
      duration: '2h 28m',
      image: 'https://assets-in.bmscdn.com/iedb/movies/images/mobile/thumbnail/xlarge/demon-slayer--kimetsu-no-yaiba--the-movie-infinity-castle-et00436673-1752221214.jpg'
    },
    {
      id: 4,
      name: 'War 2',
      description: 'High-octane spy thriller with Hrithik Roshan.',
      duration: '2h 49m',
      image: 'https://upload.wikimedia.org/wikipedia/en/thumb/f/f5/War_2_official_poster.jpg/512px-War_2_official_poster.jpg'
    },
    {
      id: 5,
      name: 'The Conjuring: Last Rites',
      description: 'Supernatural horror with Ed and Lorraine Warren.',
      duration: '2h 32m',
      image: 'https://assets-in.bmscdn.com/iedb/movies/images/mobile/thumbnail/xlarge/the-conjuring-last-rites-et00445371-1756358838.jpg'
    }
  ];

  upcomingMovies = [
    {
      id: 6,
      name: 'Kantara: Chapter 1',
      description: 'Prequel set in 300 CE during Kadamba dynasty.',
      duration: 'Coming Soon',
      image: 'https://upload.wikimedia.org/wikipedia/en/thumb/6/69/Kantara-_Chapter_1_poster.jpg/512px-Kantara-_Chapter_1_poster.jpg'
    },
    {
      id: 7,
      name: 'Sunny Sanskari Ki Tulsi Kumari',
      description: 'Fake romance turns into chaos and love.',
      duration: 'Coming Soon',
      image: 'https://upload.wikimedia.org/wikipedia/en/thumb/1/1a/Sunny_Sanskari_Ki_Tulsi_Kumari_film_poster.jpg/512px-Sunny_Sanskari_Ki_Tulsi_Kumari_film_poster.jpg'
    },
    {
      id: 8,
      name: 'Hai Jawani Toh Ishq Hona Hai',
      description:
        'An upcoming Indian Hindi-language romantic comedy film directed by David Dhawan...',
      duration: 'Coming Soon',
      image: 'https://images.justwatch.com/poster/327345901/s332/hai-jawani-toh-ishq-hona-hai.jpg'
    },
    {
      id: 9,
      name: 'De De Pyaar De 2',
      description:
        'An upcoming Indian Hindi-language romantic comedy film directed by Anshul Sharma...',
      duration: 'Coming Soon',
      image: 'https://m.media-amazon.com/images/M/MV5BNmU4ZDNmOGQtNzUxMC00MmJkLWE5MDYtZTA4NTNiOTk3OTAwXkEyXkFqcGc@.jpg'
    },
    {
      id: 10,
      name: '120 Bahadur',
      description:
        'A war epic inspired by the true story of 120 Indian soldiers who made a heroic last stand...',
      duration: 'Coming Soon',
      image: 'https://m.media-amazon.com/images/M/MV5BZjM2YTI4N2YtZGYyNS00MzZjLWE3NzctMWFhOWFlZTg5MDUyXkEyXkFqcGc@.jpg'
    }
  ];

  constructor(private router: Router) {}

  // Navigate to booking page
  bookMovie(movie: any) {
    this.router.navigate(['/user/booking'], {
      queryParams: {
        name: movie.name,
        description: movie.description,
        duration: movie.duration,
        image: movie.image
      }
    });
  }

  // Logout function
  logout() {
    this.router.navigate(['/user/login']);
  }

  // Filter movies based on search query
  get filteredAvailableMovies() {
    return this.availableMovies.filter(movie =>
      movie.name.toLowerCase().includes(this.searchQuery.toLowerCase())
    );
  }

  get filteredUpcomingMovies() {
    return this.upcomingMovies.filter(movie =>
      movie.name.toLowerCase().includes(this.searchQuery.toLowerCase())
    );
  }
}
