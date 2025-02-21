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
        Product product = new Product("id-1", "Laptop", 10);
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals("id-1", createdProduct.getProductId());
        assertEquals("Laptop", createdProduct.getProductName());
        assertEquals(10, createdProduct.getProductQuantity());

        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProducts() {
        Product product1 = new Product("id-1", "Laptop", 10);
        Product product2 = new Product("id-2", "Phone", 20);

        Iterator<Product> mockIterator = Arrays.asList(product1, product2).iterator();
        when(productRepository.findAll()).thenReturn(mockIterator);

        List<Product> products = productService.findAll();

        assertEquals(2, products.size());
        assertEquals("Laptop", products.get(0).getProductName());
        assertEquals("Phone", products.get(1).getProductName());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById_ProductFound() {
        Product product = new Product("id-1", "Laptop", 10);
        when(productRepository.findById("id-1")).thenReturn(product);

        Product foundProduct = productService.findById("id-1");

        assertNotNull(foundProduct);
        assertEquals("Laptop", foundProduct.getProductName());
        verify(productRepository, times(1)).findById("id-1");
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
        Product updatedProduct = new Product("id-1", "Updated Laptop", 15);

        when(productRepository.update(updatedProduct)).thenReturn(updatedProduct);

        Product result = productService.update(updatedProduct);

        assertNotNull(result);
        assertEquals("Updated Laptop", result.getProductName());
        assertEquals(15, result.getProductQuantity());

        verify(productRepository, times(1)).update(updatedProduct);
    }

    @Test
    void testUpdateProduct_NotFound() {
        Product updatedProduct = new Product("id-999", "Non-Existent", 50);
        when(productRepository.update(updatedProduct)).thenReturn(null);

        Product result = productService.update(updatedProduct);

        assertNull(result);
        verify(productRepository, times(1)).update(updatedProduct);
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
