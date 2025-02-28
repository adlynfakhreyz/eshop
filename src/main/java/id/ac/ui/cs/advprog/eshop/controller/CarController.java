package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController extends ItemController<Car> {

    private static final String REDIRECT_CAR_LIST = "redirect:/car/listCar";

    @Autowired
    public CarController(CarService service) {
        super(service);
    }

    @Override
    protected String getListView() {
        return "carList";
    }

    @Override
    protected String getCreateView() {
        return "createCar";
    }

    @Override
    protected String getEditView() {
        return "editCar";
    }

    @Override
    protected String getRedirectToList() {
        return REDIRECT_CAR_LIST;
    }

    @Override
    protected String getItemAttributeName() {
        return "car";
    }

    @Override
    protected String getItemsAttributeName() {
        return "cars";
    }

    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute(getItemAttributeName(), car);
        return getCreateView();
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute @Valid Car car, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return getCreateView();
        }
        service.create(car);
        return getRedirectToList();
    }

    @GetMapping("/listCar")
    public String carListPage(Model model) {
        List<Car> allCars = service.findAll();
        model.addAttribute(getItemsAttributeName(), allCars);
        return getListView();
    }

    @GetMapping("/editCar/{id}")
    public String editCarPage(@PathVariable String id, Model model) {
        Car car = service.findById(id);
        model.addAttribute(getItemAttributeName(), car);
        return getEditView();
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute @Valid Car car, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return getEditView();
        }
        service.update(car.getId(), car);
        return getRedirectToList();
    }

    @GetMapping("/deleteCar/{id}")
    public String deleteCar(@PathVariable String id) {
        service.delete(id);
        return getRedirectToList();
    }
}