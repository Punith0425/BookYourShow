package com.example.springapp.controller;

import com.example.springapp.model.Booking;
import com.example.springapp.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
@Tag(name = "Booking Management", description = "APIs for managing bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(summary = "Get all bookings", description = "Retrieve a list of all bookings")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved bookings"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        try {
            List<Booking> bookings = bookingService.getAllBookings();
            return ResponseEntity.ok(bookings);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Get booking by ID", description = "Retrieve a specific booking by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Booking found"),
        @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        return booking.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get bookings by user", description = "Retrieve bookings for a specific user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bookings found"),
        @ApiResponse(responseCode = "404", description = "No bookings found for this user")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);
        if (bookings.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookings);
    }

    @Operation(summary = "Get confirmed bookings by user", description = "Retrieve confirmed bookings for a specific user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Confirmed bookings found"),
        @ApiResponse(responseCode = "404", description = "No confirmed bookings found for this user")
    })
    @GetMapping("/user/{userId}/confirmed")
    public ResponseEntity<List<Booking>> getConfirmedBookingsByUser(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getConfirmedBookingsByUserId(userId);
        if (bookings.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookings);
    }

    @Operation(summary = "Get bookings by showtime", description = "Retrieve bookings for a specific showtime")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bookings found"),
        @ApiResponse(responseCode = "404", description = "No bookings found for this showtime")
    })
    @GetMapping("/showtime/{showtimeId}")
    public ResponseEntity<List<Booking>> getBookingsByShowtime(@PathVariable Long showtimeId) {
        List<Booking> bookings = bookingService.getBookingsByShowtimeId(showtimeId);
        if (bookings.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookings);
    }

    @Operation(summary = "Create a new booking", description = "Create a new booking")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Booking created successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid booking data or no available seats"),
    @ApiResponse(responseCode = "404", description = "Showtime not found")
})
@PostMapping
public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
    try {
        Booking createdBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(createdBooking);
    } catch (RuntimeException e) {
        if (e.getMessage().contains("Showtime not found")) {
            return ResponseEntity.notFound().build();
        } else if (e.getMessage().contains("Not enough available seats")) {
            return ResponseEntity.badRequest().body("Not enough available seats");
        }
        return ResponseEntity.badRequest().body("Invalid booking data");
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Invalid booking data");
    }
}

    @Operation(summary = "Cancel booking", description = "Cancel an existing booking")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Booking cancelled successfully"),
        @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long id) {
        boolean cancelled = bookingService.cancelBooking(id);
        return cancelled ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete booking", description = "Delete a booking by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Booking deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        boolean deleted = bookingService.deleteBooking(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get booking count by showtime", description = "Get the number of bookings for a specific showtime")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Booking count retrieved"),
        @ApiResponse(responseCode = "404", description = "Showtime not found")
    })
    @GetMapping("/showtime/{showtimeId}/count")
    public ResponseEntity<Long> getBookingCountByShowtime(@PathVariable Long showtimeId) {
        Long count = bookingService.getBookingCountByShowtime(showtimeId);
        return ResponseEntity.ok(count);
    }

}