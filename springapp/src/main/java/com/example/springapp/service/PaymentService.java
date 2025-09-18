package com.example.springapp.service;

import com.example.springapp.model.Payment;
import com.example.springapp.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * ✅ Get all payments
     */
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    /**
     * ✅ Get payment by ID
     */
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    /**
     * ✅ Create new payment
     */
    public Payment createPayment(Payment payment) {
        payment.setPaymentTime(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    /**
     * ✅ Update existing payment
     */
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // ✅ JPQL: Get payment by booking ID
    public Payment getPaymentByBookingId(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }

    // ✅ JPQL: Get all successful payments
    public List<Payment> getAllSuccessfulPayments() {
        return paymentRepository.findAllSuccessfulPayments();
    }

    /**
     * ✅ Delete payment by ID
     */
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    
}
