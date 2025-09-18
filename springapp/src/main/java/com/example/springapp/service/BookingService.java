package com.example.springapp.service;

import com.example.springapp.model.Booking;
import com.example.springapp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // 🔹 Get all bookings of a specific user (JPQL)
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findBookingsByUserId(userId);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }


    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }


    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
