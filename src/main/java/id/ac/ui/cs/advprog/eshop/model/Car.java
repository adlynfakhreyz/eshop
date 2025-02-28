package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Car extends BaseEntity {
    private String carColor;

    public Car() {
        super();
    }

    public Car(String id, String name, int quantity, String carColor) {
        super(id, name, quantity);
        this.carColor = carColor;
    }
}
