package com.example.springapp.service;

import com.example.springapp.model.Showtime;
import com.example.springapp.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }

    public Optional<Showtime> getShowtimeById(Long id) {
        return showtimeRepository.findById(id);
    }

    public Showtime createShowtime(Showtime showtime) {
        // Calculate end time based on movie duration
        if (showtime.getMovie() != null && showtime.getMovie().getDuration() != null) {
            LocalDateTime endTime = showtime.getShowTime().plusMinutes(showtime.getMovie().getDuration());
            showtime.setEndTime(endTime);
        }
        return showtimeRepository.save(showtime);
    }

    public Showtime updateShowtime(Long id, Showtime showtimeDetails) {
        Optional<Showtime> showtimeOptional = showtimeRepository.findById(id);
        if (showtimeOptional.isPresent()) {
            Showtime showtime = showtimeOptional.get();
            showtime.setShowTime(showtimeDetails.getShowTime());
            showtime.setScreenNumber(showtimeDetails.getScreenNumber());
            showtime.setTotalSeats(showtimeDetails.getTotalSeats());
            showtime.setAvailableSeats(showtimeDetails.getAvailableSeats());
            showtime.setPrice(showtimeDetails.getPrice());
            
            // Recalculate end time if show time changes
            if (showtime.getMovie() != null && showtime.getMovie().getDuration() != null) {
                LocalDateTime endTime = showtime.getShowTime().plusMinutes(showtime.getMovie().getDuration());
                showtime.setEndTime(endTime);
            }
            
            return showtimeRepository.save(showtime);
        }
        return null;
    }

    public boolean deleteShowtime(Long id) {
        if (showtimeRepository.existsById(id)) {
            showtimeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Showtime> getShowtimesByMovieId(Long movieId) {
        return showtimeRepository.findByMovieId(movieId);
    }

    public List<Showtime> getShowtimesByTheaterId(Long theaterId) {
        return showtimeRepository.findByTheaterId(theaterId);
    }

    public List<Showtime> getShowtimesByDateRange(LocalDateTime start, LocalDateTime end) {
        return showtimeRepository.findByShowTimeBetween(start, end);
    }

    public List<Showtime> getAvailableShowtimes() {
        return showtimeRepository.findAvailableShowtimes(LocalDateTime.now());
    }

    public List<Showtime> getShowtimesByMovieAndCity(Long movieId, String city) {
        return showtimeRepository.findByMovieIdAndCity(movieId, city);
    }
}