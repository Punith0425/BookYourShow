package com.example.springapp.service;

import com.example.springapp.model.Booking;
import com.example.springapp.model.Showtime;
import com.example.springapp.repository.BookingRepository;
import com.example.springapp.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    @Transactional
public Booking createBooking(Booking booking) {
    // Validate seat availability first
    if (booking.getShowtime() != null && booking.getNumberOfSeats() != null) {
        Showtime showtime = showtimeRepository.findById(booking.getShowtime().getId())
                .orElseThrow(() -> new RuntimeException("Showtime not found"));
        
        if (showtime.getAvailableSeats() < booking.getNumberOfSeats()) {
            throw new RuntimeException("Not enough available seats");
        }
        
        // Update available seats
        int newAvailableSeats = showtime.getAvailableSeats() - booking.getNumberOfSeats();
        showtime.setAvailableSeats(newAvailableSeats);
        showtimeRepository.save(showtime);
        
        // Set the managed showtime entity
        booking.setShowtime(showtime);
    }
    
    booking.setBookingDate(LocalDateTime.now());
    booking.setStatus(Booking.BookingStatus.CONFIRMED);
    
    return bookingRepository.save(booking);
}

    @Transactional
    public boolean cancelBooking(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            booking.setStatus(Booking.BookingStatus.CANCELLED);
            
            // Restore available seats in showtime
            if (booking.getShowtime() != null && booking.getNumberOfSeats() != null) {
                Showtime showtime = booking.getShowtime();
                int newAvailableSeats = showtime.getAvailableSeats() + booking.getNumberOfSeats();
                showtime.setAvailableSeats(newAvailableSeats);
                showtimeRepository.save(showtime);
            }
            
            bookingRepository.save(booking);
            return true;
        }
        return false;
    }

    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getBookingsByShowtimeId(Long showtimeId) {
        return bookingRepository.findByShowtimeId(showtimeId);
    }

    public List<Booking> getConfirmedBookingsByUserId(Long userId) {
        return bookingRepository.findConfirmedBookingsByUserId(userId);
    }

    public Long getBookingCountByShowtime(Long showtimeId) {
        return bookingRepository.countBookingsByShowtimeId(showtimeId);
    }
    
}