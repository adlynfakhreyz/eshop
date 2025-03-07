package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

class OrderTest {

    private List<Product> products;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);

        Product product2 = new Product();
        product2.setName("Sabun Cap Usep");
        product2.setQuantity(1);

        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreateOrderEmptyProduct() {
        this.products.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order(
                    "13652556-012a-4c07-b546-54eb1396d79b",
                    this.products,
                    1708560000L,
                    "Safira Sudrajat"
            );
        });
    }

    @Test
    void testCreateOrderDefaultStatus() {
        Order order = new Order(
                "13652556-012a-4c07-b546-54eb1396d79b",
                this.products,
                1708560000L,
                "Safira Sudrajat"
        );

        assertSame(this.products, order.getProducts());
        assertEquals(2, order.getProducts().size());
        assertEquals("Sampo Cap Bambang", order.getProducts().get(0).getName());
        assertEquals("Sabun Cap Usep", order.getProducts().get(1).getName());
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", order.getId());
        assertEquals(1708560000L, order.getOrderTime());
        assertEquals("Safira Sudrajat", order.getAuthor());
        assertEquals("WAITING_PAYMENT", order.getStatus());
    }

    @Test
    void testCreateOrderSuccessStatus() {
        Order order = new Order(
                "13652556-012a-4c07-b546-54eb1396d79b",
                this.products,
                1708560000L,
                "Safira Sudrajat",
                "SUCCESS"
        );
        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testCreateOrderInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order(
                    "13652556-012a-4c07-b546-54eb1396d79b",
                    this.products,
                    1708560000L,
                    "Safira Sudrajat",
                    "MEOW"
            );
        });
    }

    @Test
    void testSetStatusToCancelled() {
        Order order = new Order(
                "13652556-012a-4c07-b546-54eb1396d79b",
                this.products,
                1708560000L,
                "Safira Sudrajat"
        );
        order.setStatus("CANCELLED");
        assertEquals("CANCELLED", order.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Order order = new Order(
                "13652556-012a-4c07-b546-54eb1396d79b",
                this.products,
                1708560000L,
                "Safira Sudrajat"
        );
        assertThrows(IllegalArgumentException.class, () -> order.setStatus("MEOW"));
    }
}