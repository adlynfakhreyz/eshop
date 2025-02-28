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
        product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setId("a0f9de46-90b1-437d-a0bf-d0821d6e9096");
        product2.setName("Sampo Cap Usep");
        product2.setQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getId(), savedProduct.getId());
        savedProduct = productIterator.next();
        assertEquals(product2.getId(), savedProduct.getId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testSearchById_ProductExists() {
        // Add a product to the repository
        Product product = new Product("Gadget X", 5);
        productRepository.create(product);

        // Search for the added product
        Product retrieved = productRepository.findById(product.getId());

        // Validate that the product was found successfully
        assertNotNull(retrieved);
        assertEquals("Gadget X", retrieved.getName());
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
        Product firstProduct = new Product("Item Alpha", 8);
        Product secondProduct = new Product("Item Beta", 15);
        productRepository.create(firstProduct);
        productRepository.create(secondProduct);

        // Attempt to find the second product
        Product retrieved = productRepository.findById(secondProduct.getId());

        // Ensure the correct product is returned
        assertNotNull(retrieved);
        assertEquals("Item Beta", retrieved.getName());
    }

    @Test
    void testUpdateProduct_Successful() {
        // Add a product to the repository
        Product product = new Product("Gizmo A", 5);
        productRepository.create(product);

        // Update the existing product
        Product updatedProduct = new Product("Gizmo A Pro", 12);
        Product result = productRepository.update(product.getId(), updatedProduct);

        // Validate the update was successful
        assertNotNull(result);
        assertEquals("Gizmo A Pro", result.getName());
        assertEquals(12, result.getQuantity());
    }

    @Test
    void testUpdateProduct_NotPresent() {
        // Attempt to update a product that doesn't exist
        Product updatedProduct = new Product("Nonexistent Gadget", 25);
        Product result = productRepository.update(updatedProduct.getId(), updatedProduct);

        // The update should return null since the product is not found
        assertNull(result);
    }

    @Test
    void testUpdateProduct_MultipleEntries() {
        // Add multiple products so that the update happens in the middle of the list
        Product product1 = new Product("Gadget X", 8);
        Product product2 = new Product("Gadget Y", 16);
        productRepository.create(product1);
        productRepository.create(product2);

        // Update the second product in the list
        Product updatedProduct2 = new Product("Gadget Y Plus", 22);
        Product result = productRepository.update(product2.getId(), updatedProduct2);

        // Validate the update was applied correctly
        assertNotNull(result);
        assertEquals("Gadget Y Plus", result.getName());
        assertEquals(22, result.getQuantity());

        // Ensure the first product remains unchanged
        Product found1 = productRepository.findById(product1.getId());
        assertNotNull(found1);
        assertEquals("Gadget X", found1.getName());
    }

    @Test
    void testDeleteProduct_Successful() {
        // Add a product and then delete it
        Product product = new Product("Widget Alpha", 10);
        productRepository.create(product);
        productRepository.delete(product.getId());

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