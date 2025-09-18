package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springapp.model.Showtime;

import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    // Find showtimes for a movie
    @Query("SELECT s FROM Showtime s WHERE s.movie.id = :movieId")
    List<Showtime> findShowtimesByMovieId(@Param("movieId") Long movieId);

    // Find showtimes in a theater
    @Query("SELECT s FROM Showtime s WHERE s.theater.id = :theaterId")
    List<Showtime> findShowtimesByTheaterId(@Param("theaterId") Long theaterId);

    // Find upcoming showtimes
    @Query("SELECT s FROM Showtime s WHERE s.startTime > CURRENT_TIMESTAMP")
    List<Showtime> findUpcomingShowtimes();
}
