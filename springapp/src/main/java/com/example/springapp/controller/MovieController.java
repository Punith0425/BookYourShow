package com.example.springapp.controller;

import com.example.springapp.model.Movie;
import com.example.springapp.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "http://localhost:4200") // Optional for Angular/React frontend
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * ✅ Get all movies
     */
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    /**
     * ✅ Get movie by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        return movie.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * ✅ Get movies by genre (JPQL)
     */
    @GetMapping("/genre/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre) {
        return movieService.getMoviesByGenre(genre);
    }

    /**
     * ✅ Get movies released after a specific date (JPQL)
     */
    @GetMapping("/released-after/{releaseDate}")
    public List<Movie> getMoviesReleasedAfter(@PathVariable String releaseDate) {
        return movieService.getMoviesReleasedAfter(releaseDate);
    }

    /**
     * ✅ Create new movie
     */
    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie newMovie = movieService.createMovie(movie);
        return ResponseEntity.ok(newMovie);
    }

    /**
     * ✅ Update movie
     */
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie updatedMovie) {
        Optional<Movie> existingMovie = movieService.getMovieById(id);

        if (existingMovie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        updatedMovie.setId(id);
        Movie savedMovie = movieService.updateMovie(updatedMovie);
        return ResponseEntity.ok(savedMovie);
    }

    /**
     * ✅ Delete movie by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        Optional<Movie> existingMovie = movieService.getMovieById(id);

        if (existingMovie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paged")
    public Page<Movie> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return movieService.getAllMovies(page, size, sortBy);
    }
}
