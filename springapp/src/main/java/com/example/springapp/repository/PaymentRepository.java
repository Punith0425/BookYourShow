package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springapp.model.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Find payment by booking
    @Query("SELECT p FROM Payment p WHERE p.booking.id = :bookingId")
    Payment findByBookingId(@Param("bookingId") Long bookingId);

    // Find all successful payments
    @Query("SELECT p FROM Payment p WHERE p.status = com.example.springapp.model.Payment$PaymentStatus.SUCCESS")
    List<Payment> findAllSuccessfulPayments();
}
