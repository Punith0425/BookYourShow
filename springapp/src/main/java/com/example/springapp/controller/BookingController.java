package com.example.springapp.controller;

import com.example.springapp.model.Booking;
import com.example.springapp.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular/React frontend (optional)
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

     // 🔹 Get all bookings of a user (JPQL)
    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUserId(@PathVariable Long userId) {
        return bookingService.getBookingsByUserId(userId);
    }

    /**
     * ✅ Get all bookings
     */
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    /**
     * ✅ Get booking by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        return booking.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * ✅ Create a new booking
     */
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking newBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(newBooking);
    }

    /**
     * ✅ Update booking
     */
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        Optional<Booking> existingBooking = bookingService.getBookingById(id);

        if (existingBooking.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        updatedBooking.setId(id);
        Booking savedBooking = bookingService.updateBooking(updatedBooking);
        return ResponseEntity.ok(savedBooking);
    }

    /**
     * ✅ Delete booking by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        Optional<Booking> existingBooking = bookingService.getBookingById(id);

        if (existingBooking.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
