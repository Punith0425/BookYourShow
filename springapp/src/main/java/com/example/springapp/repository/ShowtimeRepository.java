package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.springapp.model.Showtime;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    List<Showtime> findByMovieId(Long movieId);
    List<Showtime> findByTheaterId(Long theaterId);
    List<Showtime> findByShowTimeBetween(LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT s FROM Showtime s WHERE s.movie.id = :movieId AND s.theater.city = :city")
    List<Showtime> findByMovieIdAndCity(@Param("movieId") Long movieId, @Param("city") String city);
    
    @Query("SELECT s FROM Showtime s WHERE s.availableSeats > 0 AND s.showTime > :currentTime")
    List<Showtime> findAvailableShowtimes(@Param("currentTime") LocalDateTime currentTime);
}