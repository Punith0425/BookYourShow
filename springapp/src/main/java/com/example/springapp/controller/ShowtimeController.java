package com.example.springapp.controller;

import com.example.springapp.model.Showtime;
import com.example.springapp.service.ShowtimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/showtimes")
@CrossOrigin(origins = "http://localhost:4200") // Enable CORS for Angular/React
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    /**
     * ✅ Get all showtimes
     */
    @GetMapping
    public List<Showtime> getAllShowtimes() {
        return showtimeService.getAllShowtimes();
    }

    /**
     * ✅ Get a specific showtime by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Showtime> getShowtimeById(@PathVariable Long id) {
        Optional<Showtime> showtime = showtimeService.getShowtimeById(id);
        return showtime.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * ✅ Create a new showtime
     */
    @PostMapping
    public ResponseEntity<Showtime> createShowtime(@RequestBody Showtime showtime) {
        Showtime newShowtime = showtimeService.createShowtime(showtime);
        return ResponseEntity.ok(newShowtime);
    }

    /**
     * ✅ Update an existing showtime
     */
    @PutMapping("/{id}")
    public ResponseEntity<Showtime> updateShowtime(@PathVariable Long id, @RequestBody Showtime updatedShowtime) {
        Optional<Showtime> existingShowtime = showtimeService.getShowtimeById(id);

        if (existingShowtime.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        updatedShowtime.setId(id);
        Showtime savedShowtime = showtimeService.updateShowtime(updatedShowtime);
        return ResponseEntity.ok(savedShowtime);
    }

    /**
     * ✅ Delete a showtime
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable Long id) {
        Optional<Showtime> existingShowtime = showtimeService.getShowtimeById(id);

        if (existingShowtime.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        showtimeService.deleteShowtime(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ JPQL: Get showtimes by movie ID
    @GetMapping("/movie/{movieId}")
    public List<Showtime> getShowtimesByMovie(@PathVariable Long movieId) {
        return showtimeService.getShowtimesByMovieId(movieId);
    }

    // ✅ JPQL: Get showtimes by theater ID
    @GetMapping("/theater/{theaterId}")
    public List<Showtime> getShowtimesByTheater(@PathVariable Long theaterId) {
        return showtimeService.getShowtimesByTheaterId(theaterId);
    }

    // ✅ JPQL: Get upcoming showtimes
    @GetMapping("/upcoming")
    public List<Showtime> getUpcomingShowtimes() {
        return showtimeService.getUpcomingShowtimes();
    }
}
