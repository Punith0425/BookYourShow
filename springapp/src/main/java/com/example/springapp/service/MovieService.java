package com.example.springapp.service;

import com.example.springapp.model.Movie;
import com.example.springapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * ✅ Get all movies
     */
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // 🔹 Get movies by genre (JPQL)
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findMoviesByGenre(genre);
    }

    // 🔹 Get movies released after a specific date (JPQL)
    public List<Movie> getMoviesReleasedAfter(String releaseDate) {
        return movieRepository.findMoviesReleasedAfter(releaseDate);
    }

    /**
     * ✅ Get movie by ID
     */
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    /**
     * ✅ Create a new movie
     */
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    /**
     * ✅ Update existing movie
     */
    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    /**
     * ✅ Delete movie by ID
     */
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    // ✅ Paginated & Sorted Movies
    public Page<Movie> getAllMovies(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return movieRepository.findAll(pageable);
    }

}
