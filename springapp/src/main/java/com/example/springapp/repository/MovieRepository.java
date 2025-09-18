package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springapp.model.Movie;

import java.util.List;
 
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // Find movies by genre (case-insensitive)
    @Query("SELECT m FROM Movie m WHERE LOWER(m.genre) = LOWER(:genre)")
    List<Movie> findMoviesByGenre(@Param("genre") String genre);

    // Find movies released after a given releaseDate
    @Query("SELECT m FROM Movie m WHERE m.releaseDate > :releaseDate")
    List<Movie> findMoviesReleasedAfter(@Param("releaseDate") String releaseDate);
}
