package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Car extends AbstractItem {
    private String carColor;

    public Car() {
        super();
    }

    public Car(String name, int quantity, String carColor) {
        super(name, quantity);
        this.carColor = carColor;
    }
}