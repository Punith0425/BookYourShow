package com.example.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.springapp.model.Payment;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUserId(Long userId);
    List<Payment> findByBookingId(Long bookingId);
    Payment findByTransactionId(String transactionId);
    
    @Query("SELECT p FROM Payment p WHERE p.status = 'SUCCESS' AND p.user.id = :userId")
    List<Payment> findSuccessfulPaymentsByUserId(@Param("userId") Long userId);
}