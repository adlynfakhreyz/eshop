package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment) {
        // Implementation will be added later
        return payment;
    }

    public Payment findById(String id) {
        // Implementation will be added later
        return null;
    }

    public List<Payment> findAll() {
        return this.paymentData;
    }
}