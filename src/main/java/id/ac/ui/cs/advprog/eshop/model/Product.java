package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product extends BaseEntity {
    public Product() {
        super();
    }

    public Product(String id, String name, int quantity) {
        super(id, name, quantity);
    }
}
