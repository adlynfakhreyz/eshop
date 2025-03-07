package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Payment payment;
    private Order order;
    private List<Product> products;
    private Map<String, String> validVoucherData;
    private Map<String, String> invalidVoucherData;
    private Map<String, String> validCodData;
    private Map<String, String> invalidCodData;

    @BeforeEach
    void setUp() {
        Product product = new Product();
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);

        products = new ArrayList<>();
        products.add(product);

        order = new Order("order-id", products, 1708560000L, "Bambang");

        validVoucherData = new HashMap<>();
        validVoucherData.put("voucherCode", "ESHOP1234ABCD5678");

        invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "INVALID");

        validCodData = new HashMap<>();
        validCodData.put("address", "Jalan Merdeka No. 123");
        validCodData.put("deliveryFee", "10000");

        invalidCodData = new HashMap<>();
        invalidCodData.put("address", "Jalan Merdeka No. 123");
        // Missing deliveryFee

        payment = new Payment("payment-id", order, "VOUCHER", validVoucherData);
    }

    @Test
    void testAddPaymentWithValidVoucher() {
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> {
            Payment p = invocation.getArgument(0);
            return p;
        });

        Payment result = paymentService.addPayment(order, "VOUCHER", validVoucherData);

        assertNotNull(result);
        assertEquals("SUCCESS", result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentWithInvalidVoucher() {
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> {
            Payment p = invocation.getArgument(0);
            return p;
        });

        Payment result = paymentService.addPayment(order, "VOUCHER", invalidVoucherData);

        assertNotNull(result);
        assertEquals("REJECTED", result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentWithValidCashOnDelivery() {
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> {
            Payment p = invocation.getArgument(0);
            return p;
        });

        Payment result = paymentService.addPayment(order, "CASH_ON_DELIVERY", validCodData);

        assertNotNull(result);
        assertEquals("SUCCESS", result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentWithInvalidCashOnDelivery() {
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> {
            Payment p = invocation.getArgument(0);
            return p;
        });

        Payment result = paymentService.addPayment(order, "CASH_ON_DELIVERY", invalidCodData);

        assertNotNull(result);
        assertEquals("REJECTED", result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusToSuccess() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
        when(orderService.updateStatus(order.getId(), "SUCCESS")).thenReturn(order);

        Payment result = paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", result.getStatus());
        verify(orderService, times(1)).updateStatus(order.getId(), "SUCCESS");
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testSetStatusToRejected() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
        when(orderService.updateStatus(order.getId(), "FAILED")).thenReturn(order);

        Payment result = paymentService.setStatus(payment, "REJECTED");

        assertEquals("REJECTED", result.getStatus());
        verify(orderService, times(1)).updateStatus(order.getId(), "FAILED");
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testGetPayment() {
        when(paymentRepository.findById("payment-id")).thenReturn(payment);

        Payment result = paymentService.getPayment("payment-id");

        assertNotNull(result);
        assertEquals(payment, result);
    }

    @Test
    void testGetPaymentNotFound() {
        when(paymentRepository.findById("non-existent")).thenReturn(null);

        assertThrows(NoSuchElementException.class, () -> {
            paymentService.getPayment("non-existent");
        });
    }

    @Test
    void testGetAllPayments() {
        List<Payment> expectedPayments = new ArrayList<>();
        expectedPayments.add(payment);

        when(paymentRepository.findAll()).thenReturn(expectedPayments);

        List<Payment> result = paymentService.getAllPayments();

        assertNotNull(result);
        assertEquals(expectedPayments.size(), result.size());
        assertEquals(expectedPayments, result);
    }
}