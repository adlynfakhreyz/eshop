package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;
    private static final String REDIRECT_PRODUCT_LIST = "redirect:/product/list";

    // Constructor Injection
    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }
    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "createProduct"; // Return to form if validation fails
        }

        service.create(product);
        return REDIRECT_PRODUCT_LIST;
    }
    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable String id, Model model) {
        Product product = service.findById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute Product product) {
        service.update(product);
        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        service.delete(id);
        return REDIRECT_PRODUCT_LIST;
    }
}