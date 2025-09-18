package com.example.springapp.service;

import com.example.springapp.model.Showtime;
import com.example.springapp.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService {

    private final ShowtimeRepository showtimeRepository;

    @Autowired
    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    /**
     * ✅ Get all showtimes
     */
    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }

    /**
     * ✅ Get a showtime by ID
     */
    public Optional<Showtime> getShowtimeById(Long id) {
        return showtimeRepository.findById(id);
    }

    /**
     * ✅ Create a new showtime
     */
    public Showtime createShowtime(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    /**
     * ✅ Update an existing showtime
     */
    public Showtime updateShowtime(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    /**
     * ✅ Delete a showtime by ID
     */
    public void deleteShowtime(Long id) {
        showtimeRepository.deleteById(id);
    }

    // ✅ JPQL: Find showtimes for a specific movie
    public List<Showtime> getShowtimesByMovieId(Long movieId) {
        return showtimeRepository.findShowtimesByMovieId(movieId);
    }

    // ✅ JPQL: Find showtimes in a specific theater
    public List<Showtime> getShowtimesByTheaterId(Long theaterId) {
        return showtimeRepository.findShowtimesByTheaterId(theaterId);
    }

    // ✅ JPQL: Find upcoming showtimes
    public List<Showtime> getUpcomingShowtimes() {
        return showtimeRepository.findUpcomingShowtimes();
    }
}
