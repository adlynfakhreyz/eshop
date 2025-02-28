package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product extends AbstractItem {
    public Product() {
        super();
    }

    public Product(String name, int quantity) {
        super(name, quantity);
    }
}