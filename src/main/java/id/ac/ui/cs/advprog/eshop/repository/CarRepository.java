package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepository extends AbstractItemRepository<Car> {
    @Override
    public Car update(String id, Car updatedCar) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                Car car = items.get(i);
                car.setName(updatedCar.getName());
                car.setCarColor(updatedCar.getCarColor());
                car.setQuantity(updatedCar.getQuantity());
                return car;
            }
        }
        return null;
    }
}