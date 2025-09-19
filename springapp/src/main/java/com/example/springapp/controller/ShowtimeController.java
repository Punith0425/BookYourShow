package com.example.springapp.controller;

import com.example.springapp.model.Showtime;
import com.example.springapp.service.ShowtimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/showtimes")
@CrossOrigin(origins = "*")
@Tag(name = "Showtime Management", description = "APIs for managing showtimes")
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @Operation(summary = "Get all showtimes", description = "Retrieve a list of all showtimes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved showtimes"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<Showtime>> getAllShowtimes() {
        try {
            List<Showtime> showtimes = showtimeService.getAllShowtimes();
            return ResponseEntity.ok(showtimes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Get showtime by ID", description = "Retrieve a specific showtime by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Showtime found"),
        @ApiResponse(responseCode = "404", description = "Showtime not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Showtime> getShowtimeById(@PathVariable Long id) {
        Optional<Showtime> showtime = showtimeService.getShowtimeById(id);
        return showtime.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get showtimes by movie", description = "Retrieve showtimes for a specific movie")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Showtimes found"),
        @ApiResponse(responseCode = "404", description = "No showtimes found for this movie")
    })
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Showtime>> getShowtimesByMovie(@PathVariable Long movieId) {
        List<Showtime> showtimes = showtimeService.getShowtimesByMovieId(movieId);
        if (showtimes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(showtimes);
    }

    @Operation(summary = "Get showtimes by theater", description = "Retrieve showtimes for a specific theater")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Showtimes found"),
        @ApiResponse(responseCode = "404", description = "No showtimes found for this theater")
    })
    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<Showtime>> getShowtimesByTheater(@PathVariable Long theaterId) {
        List<Showtime> showtimes = showtimeService.getShowtimesByTheaterId(theaterId);
        if (showtimes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(showtimes);
    }

    @Operation(summary = "Get available showtimes", description = "Retrieve showtimes with available seats")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Available showtimes found"),
        @ApiResponse(responseCode = "404", description = "No available showtimes")
    })
    @GetMapping("/available")
    public ResponseEntity<List<Showtime>> getAvailableShowtimes() {
        List<Showtime> showtimes = showtimeService.getAvailableShowtimes();
        if (showtimes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(showtimes);
    }

    @Operation(summary = "Create a new showtime", description = "Add a new showtime to the database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Showtime created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid showtime data")
    })
    @PostMapping
    public ResponseEntity<Showtime> createShowtime(@RequestBody Showtime showtime) {
        try {
            Showtime createdShowtime = showtimeService.createShowtime(showtime);
            return ResponseEntity.ok(createdShowtime);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Update showtime", description = "Update an existing showtime's information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Showtime updated successfully"),
        @ApiResponse(responseCode = "404", description = "Showtime not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Showtime> updateShowtime(@PathVariable Long id, @RequestBody Showtime showtimeDetails) {
        Showtime updatedShowtime = showtimeService.updateShowtime(id, showtimeDetails);
        if (updatedShowtime != null) {
            return ResponseEntity.ok(updatedShowtime);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete showtime", description = "Delete a showtime by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Showtime deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Showtime not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable Long id) {
        boolean deleted = showtimeService.deleteShowtime(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}