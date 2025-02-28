package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product("Laptop", 10);
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals("Laptop", createdProduct.getName());
        assertEquals(10, createdProduct.getQuantity());

        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProducts() {
        Product product1 = new Product("Laptop", 10);
        Product product2 = new Product("Phone", 20);

        Iterator<Product> mockIterator = Arrays.asList(product1, product2).iterator();
        when(productRepository.findAll()).thenReturn(mockIterator);

        List<Product> products = productService.findAll();

        assertEquals(2, products.size());
        assertEquals("Laptop", products.get(0).getName());
        assertEquals("Phone", products.get(1).getName());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById_ProductFound() {
        String productId = "id-1"; // Explicitly define the ID
        Product product = new Product("Laptop", 10);

        when(productRepository.findById(productId)).thenReturn(product); // Use explicit ID

        Product foundProduct = productService.findById(productId); // Use the same ID

        assertNotNull(foundProduct);
        assertEquals("Laptop", foundProduct.getName());

        verify(productRepository, times(1)).findById(productId); // Use the correct ID
    }

    @Test
    void testFindById_ProductNotFound() {
        when(productRepository.findById("invalid-id")).thenReturn(null);

        Product foundProduct = productService.findById("invalid-id");

        assertNull(foundProduct);
        verify(productRepository, times(1)).findById("invalid-id");
    }

    @Test
    void testUpdateProduct_Success() {
        String productId = "id-1";
        Product updatedProduct = new Product("Updated Laptop", 15);

        when(productRepository.update(productId, updatedProduct)).thenReturn(updatedProduct);

        Product result = productService.update(productId, updatedProduct);

        assertNotNull(result);
        assertEquals("Updated Laptop", result.getName());
        assertEquals(15, result.getQuantity());

        verify(productRepository, times(1)).update(productId, updatedProduct);
    }

    @Test
    void testUpdateProduct_NotFound() {
        String productId = "non-existent-id";
        Product updatedProduct = new Product("Non-Existent", 50);

        when(productRepository.update(productId, updatedProduct)).thenReturn(null);

        Product result = productService.update(productId, updatedProduct);

        assertNull(result);
        verify(productRepository, times(1)).update(productId, updatedProduct);
    }


    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete("id-1");

        assertDoesNotThrow(() -> productService.delete("id-1"));

        verify(productRepository, times(1)).delete("id-1");
    }

    @Test
    void testDeleteProduct_NotFound() {
        doNothing().when(productRepository).delete("invalid-id");

        assertDoesNotThrow(() -> productService.delete("invalid-id"));

        verify(productRepository, times(1)).delete("invalid-id");
    }
}
