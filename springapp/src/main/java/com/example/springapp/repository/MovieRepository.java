package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.springapp.model.Movie;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContainingIgnoreCase(String title);
    
    // 🔹 Pagination methods
    Page<Movie> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Movie> findByGenreContainingIgnoreCase(String genre, Pageable pageable);
    
    List<Movie> findByGenreContainingIgnoreCase(String genre);
    List<Movie> findByLanguage(String language);
    
    @Query("SELECT m FROM Movie m WHERE m.rating >= :minRating")
    List<Movie> findByRatingGreaterThanEqual(@Param("minRating") Double minRating);
}