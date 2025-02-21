package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.model.Product;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        // This method is executed before each test case.
        // It is typically used to initialize test data or reset the state of dependencies.
        // Currently, it is empty because no setup is needed before running the tests.
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821d6e9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testSearchById_ProductExists() {
        // Add a product to the repository
        Product product = new Product("p-101", "Gadget X", 5);
        productRepository.create(product);

        // Search for the added product
        Product retrieved = productRepository.findById("p-101");

        // Validate that the product was found successfully
        assertNotNull(retrieved);
        assertEquals("Gadget X", retrieved.getProductName());
    }

    @Test
    void testSearchById_ProductNotExists() {
        // Attempt to find a product that doesn't exist in the repository
        Product retrieved = productRepository.findById("unknown-999");

        // Ensure the search result is null
        assertNull(retrieved);
    }

    @Test
    void testSearchById_AmongMultipleEntries() {
        // Insert multiple products to simulate a real-case scenario
        Product firstProduct = new Product("p-202", "Item Alpha", 8);
        Product secondProduct = new Product("p-303", "Item Beta", 15);
        productRepository.create(firstProduct);
        productRepository.create(secondProduct);

        // Attempt to find the second product
        Product retrieved = productRepository.findById("p-303");

        // Ensure the correct product is returned
        assertNotNull(retrieved);
        assertEquals("Item Beta", retrieved.getProductName());
    }

    @Test
    void testUpdateProduct_Successful() {
        // Add a product to the repository
        Product product = new Product("p-100", "Gizmo A", 5);
        productRepository.create(product);

        // Update the existing product
        Product updatedProduct = new Product("p-100", "Gizmo A Pro", 12);
        Product result = productRepository.update(updatedProduct);

        // Validate the update was successful
        assertNotNull(result);
        assertEquals("Gizmo A Pro", result.getProductName());
        assertEquals(12, result.getProductQuantity());
    }

    @Test
    void testUpdateProduct_NotPresent() {
        // Attempt to update a product that doesn't exist
        Product updatedProduct = new Product("p-999", "Nonexistent Gadget", 25);
        Product result = productRepository.update(updatedProduct);

        // The update should return null since the product is not found
        assertNull(result);
    }

    @Test
    void testUpdateProduct_MultipleEntries() {
        // Add multiple products so that the update happens in the middle of the list
        Product product1 = new Product("p-101", "Gadget X", 8);
        Product product2 = new Product("p-102", "Gadget Y", 16);
        productRepository.create(product1);
        productRepository.create(product2);

        // Update the second product in the list
        Product updatedProduct2 = new Product("p-102", "Gadget Y Plus", 22);
        Product result = productRepository.update(updatedProduct2);

        // Validate the update was applied correctly
        assertNotNull(result);
        assertEquals("Gadget Y Plus", result.getProductName());
        assertEquals(22, result.getProductQuantity());

        // Ensure the first product remains unchanged
        Product found1 = productRepository.findById("p-101");
        assertNotNull(found1);
        assertEquals("Gadget X", found1.getProductName());
    }

    @Test
    void testDeleteProduct_Successful() {
        // Add a product and then delete it
        Product product = new Product("p-200", "Widget Alpha", 10);
        productRepository.create(product);
        productRepository.delete("p-200");

        // Verify that the repository is now empty
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProduct_NotFound() {
        // Attempt to delete a product that does not exist
        assertDoesNotThrow(() -> productRepository.delete("p-999"));
    }

}