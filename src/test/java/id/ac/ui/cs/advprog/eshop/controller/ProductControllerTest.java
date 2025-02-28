package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"));
    }

    @Test
    void testCreateProductPost_ValidProduct() throws Exception {
        mockMvc.perform(post("/product/create")
                        .param("id", "1")
                        .param("name", "Laptop")
                        .param("quantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    void testCreateProductPost_WithValidationErrors() throws Exception {
        mockMvc.perform(post("/product/create")
                        .param("id", "1")
                        .param("name", "") // Empty name (invalid)
                        .param("quantity", "-5")) // Negative quantity (invalid)
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"));

        verify(productService, never()).create(any(Product.class)); // Ensure service.create is NOT called
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> productList = Arrays.asList(
                new Product("Laptop", 10),
                new Product("Phone", 20)
        );

        when(productService.findAll()).thenReturn(productList);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attributeExists("products"));

        verify(productService, times(1)).findAll();
    }

    @Test
    void testEditProductPage() throws Exception {
        Product product = new Product("Laptop", 10);
        String productId = "1"; // Manually setting the ID
        when(productService.findById(productId)).thenReturn(product);

        mockMvc.perform(get("/product/edit/" + productId))
                .andExpect(status().isOk())
                .andExpect(view().name("editProduct"))
                .andExpect(model().attributeExists("product"));

        verify(productService, times(1)).findById(productId);
    }

//    @Test
//    void testEditProductPost() throws Exception {
//        // Manually set an ID for the product
//        Product product = new Product("name", 14);
//        String productId = "1";
//        product.setId(productId);
//
//        when(productService.findById(productId)).thenReturn(product);
//
//        mockMvc.perform(post("/product/edit/" + productId)
//                        .param("name", "Updated Laptop")
//                        .param("quantity", "15"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/product/list"));
//
//        // Fix the verification: Compare ID correctly and use any(Product.class)
//        verify(productService, times(1)).update(eq(productId), any(Product.class));
//    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(get("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).delete("1");
    }
}
