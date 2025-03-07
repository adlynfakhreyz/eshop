package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Payment payment;
    private Order order;
    private List<Product> products;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        Product product = new Product();
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);

        products = new ArrayList<>();
        products.add(product);

        order = new Order("order-id", products, 1708560000L, "Bambang");

        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABCD5678");
    }

    @Test
    void testCreatePaymentWithEmptyMethod() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment = new Payment("payment-id", order, "", paymentData);
        });
        assertEquals("Payment method cannot be null or empty", exception.getMessage());
    }

    @Test
    void testCreatePaymentWithNullMethod() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment = new Payment("payment-id", order, null, paymentData);
        });
        assertEquals("Payment method cannot be null or empty", exception.getMessage());
    }

    @Test
    void testCreatePayment() {
        payment = new Payment("payment-id", order, "VOUCHER", paymentData);
        assertEquals("payment-id", payment.getId());
        assertEquals(order, payment.getOrder());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("WAITING", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testSetStatus() {
        payment = new Payment("payment-id", order, "VOUCHER", paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetInvalidStatus() {
        payment = new Payment("payment-id", order, "VOUCHER", paymentData);
        payment.setStatus("INVALID");
        // Should stay with the original status
        assertEquals("WAITING", payment.getStatus());
    }
}