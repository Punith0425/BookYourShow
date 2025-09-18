package com.example.springapp.controller;

import com.example.springapp.model.Payment;
import com.example.springapp.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:4200") // Enable if using Angular/React
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * ✅ Get all payments
     */
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    /**
     * ✅ Get payment by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Optional<Payment> payment = paymentService.getPaymentById(id);
        return payment.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    /**
     * ✅ Create new payment
     */
    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment newPayment = paymentService.createPayment(payment);
        return ResponseEntity.ok(newPayment);
    }

    /**
     * ✅ Update payment by ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment updatedPayment) {
        Optional<Payment> existingPayment = paymentService.getPaymentById(id);

        if (existingPayment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        updatedPayment.setId(id);
        Payment savedPayment = paymentService.updatePayment(updatedPayment);
        return ResponseEntity.ok(savedPayment);
    }

    /**
     * ✅ Delete payment by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        Optional<Payment> existingPayment = paymentService.getPaymentById(id);

        if (existingPayment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * ✅ JPQL: Get payment by booking ID
     */
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<Payment> getPaymentByBookingId(@PathVariable Long bookingId) {
        Payment payment = paymentService.getPaymentByBookingId(bookingId);
        return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.notFound().build();
    }

    /**
     * ✅ JPQL: Get all successful payments
     */
    @GetMapping("/successful")
    public List<Payment> getAllSuccessfulPayments() {
        return paymentService.getAllSuccessfulPayments();
    }
}
