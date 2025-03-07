package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    Payment payment1;
    Payment payment2;
    Order order1;
    Order order2;
    List<Product> products;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        Product product = new Product();
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);

        products = new ArrayList<>();
        products.add(product);

        order1 = new Order("order-1", products, 1708560000L, "Bambang");
        order2 = new Order("order-2", products, 1708560000L, "Usep");

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABCD5678");

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("address", "Jalan Merdeka No. 123");
        paymentData2.put("deliveryFee", "10000");

        payment1 = new Payment("payment-1", order1, "VOUCHER", paymentData1);
        payment2 = new Payment("payment-2", order2, "CASH_ON_DELIVERY", paymentData2);
    }

    @Test
    void testSaveAddPayment() {
        Payment savedPayment = paymentRepository.save(payment1);

        assertEquals(payment1.getId(), savedPayment.getId());
        assertEquals(1, paymentRepository.findAll().size());
        assertEquals(payment1, paymentRepository.findAll().get(0));
    }

    @Test
    void testSaveUpdatePayment() {
        paymentRepository.save(payment1);
        payment1.setStatus("SUCCESS");
        Payment updatedPayment = paymentRepository.save(payment1);

        assertEquals("SUCCESS", updatedPayment.getStatus());
        assertEquals(1, paymentRepository.findAll().size());
        assertEquals("SUCCESS", paymentRepository.findAll().get(0).getStatus());
    }

    @Test
    void testFindByIdIfFound() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        Payment foundPayment = paymentRepository.findById("payment-1");

        assertNotNull(foundPayment);
        assertEquals(payment1.getId(), foundPayment.getId());
    }

    @Test
    void testFindByIdIfNotFound() {
        paymentRepository.save(payment1);

        Payment foundPayment = paymentRepository.findById("non-existent-id");

        assertNull(foundPayment);
    }
}