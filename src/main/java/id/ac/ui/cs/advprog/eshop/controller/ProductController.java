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
public class ProductController extends ItemController<Product> {

    private static final String REDIRECT_PRODUCT_LIST = "redirect:/product/list";

    @Autowired
    public ProductController(ProductService service) {
        super(service);
    }

    @Override
    protected String getListView() {
        return "productList";
    }

    @Override
    protected String getCreateView() {
        return "createProduct";
    }

    @Override
    protected String getEditView() {
        return "editProduct";
    }

    @Override
    protected String getRedirectToList() {
        return REDIRECT_PRODUCT_LIST;
    }

    @Override
    protected String getItemAttributeName() {
        return "product";
    }

    @Override
    protected String getItemsAttributeName() {
        return "products";
    }

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute(getItemAttributeName(), product);
        return getCreateView();
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return getCreateView();
        }
        service.create(product);
        return getRedirectToList();
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute(getItemsAttributeName(), allProducts);
        return getListView();
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable String id, Model model) {
        Product product = service.findById(id);
        model.addAttribute(getItemAttributeName(), product);
        return getEditView();
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return getEditView();
        }
        service.update(product.getId(), product);
        return getRedirectToList();
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("id") String id) {
        service.delete(id);
        return getRedirectToList();
    }
}