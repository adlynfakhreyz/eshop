package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter @Setter
public class Product {
    private String productId;

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotNull(message = "Product quantity is required")
    @Min(value = 0, message = "Product quantity must be greater than or equal to 0")
    private Integer productQuantity;

    public Product() {
        this.productId = UUID.randomUUID().toString();
    }

    public Product(String productId, String productName, int productQuantity) {
        this.productId = productId != null ? productId : UUID.randomUUID().toString();
        this.productName = productName;
        this.productQuantity = productQuantity;
    }
}