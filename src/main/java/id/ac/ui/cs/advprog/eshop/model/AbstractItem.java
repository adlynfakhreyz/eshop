package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Getter @Setter
public abstract class AbstractItem {
    private String id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Integer quantity;

    protected AbstractItem() {
        this.id = UUID.randomUUID().toString();
    }

    protected AbstractItem(String name, int quantity) {
        this();
        this.name = name;
        this.quantity = quantity;
    }
}