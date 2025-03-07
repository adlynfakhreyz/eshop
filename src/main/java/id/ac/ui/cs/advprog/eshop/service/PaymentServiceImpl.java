package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderService orderService) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
    }

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String paymentId = UUID.randomUUID().toString();
        Payment payment = new Payment(paymentId, order, method, paymentData);

        // Set status based on payment method validation
        if (method.equals(PaymentMethod.VOUCHER.getValue())) {
            validateVoucherPayment(payment);
        } else if (method.equals(PaymentMethod.CASH_ON_DELIVERY.getValue())) {
            validateCashOnDeliveryPayment(payment);
        }

        return paymentRepository.save(payment);
    }

    private void validateVoucherPayment(Payment payment) {
        String voucherCode = payment.getPaymentData().get("voucherCode");

        // Check if voucher code is valid (16 chars, starts with "ESHOP", has 8 numeric chars)
        boolean isValid = voucherCode != null &&
                voucherCode.length() == 16 &&
                voucherCode.startsWith("ESHOP") &&
                countNumericChars(voucherCode) == 8;

        payment.setStatus(isValid ?
                PaymentStatus.SUCCESS.getValue() :
                PaymentStatus.REJECTED.getValue());
    }

    private void validateCashOnDeliveryPayment(Payment payment) {
        String address = payment.getPaymentData().get("address");
        String deliveryFee = payment.getPaymentData().get("deliveryFee");

        boolean isValid = address != null && !address.isEmpty() &&
                deliveryFee != null && !deliveryFee.isEmpty();

        payment.setStatus(isValid ?
                PaymentStatus.SUCCESS.getValue() :
                PaymentStatus.REJECTED.getValue());
    }

    private int countNumericChars(String str) {
        return (int) str.chars()
                .filter(Character::isDigit)
                .count();
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);

        // Update order status based on payment status
        if (status.equals(PaymentStatus.SUCCESS.getValue())) {
            orderService.updateStatus(payment.getOrder().getId(), "SUCCESS");
        } else if (status.equals(PaymentStatus.REJECTED.getValue())) {
            orderService.updateStatus(payment.getOrder().getId(), "FAILED");
        }

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(String paymentId) {
        Payment payment = paymentRepository.findById(paymentId);
        if (payment == null) {
            throw new NoSuchElementException("Payment not found");
        }
        return payment;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}