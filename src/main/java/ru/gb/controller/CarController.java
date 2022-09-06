package ru.gb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gb.entities.Car;
import ru.gb.services.CarService;
import ru.gb.services.ColorService;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private ColorService colorService;


    @GetMapping("/all")
    public String getAllCars(Model model) {
        List<Car> cars = carService.findAllCars();
        model.addAttribute("cars", cars);
        return "all-cars";
    }

    @GetMapping("/delete/{id}")
    public String deleteCarById(Model model, @PathVariable Long id) {
        carService.deleteCarById(id);
        return "redirect:/car/all";
    }

    @GetMapping("/new")
    public String addNewCar(Model model) {
        model.addAttribute("newCar", new Car());
        model.addAttribute("colors", colorService.getColors());
        return "car-form";
    }

    @PostMapping("/new")
    public String addNewCar(Model model, @Valid @ModelAttribute("newCar") Car newCar, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            model.addAttribute("newCar", newCar);
            model.addAttribute("colors", colorService.getColors());
            return "car-form";
        }
        Car existing = carService.findByModel(newCar.getModel());
        if (existing != null) {
            model.addAttribute("newCar", newCar);
            model.addAttribute("addCarError", "Car with current model already exists");
            return "car-form";
        }
        carService.saveCar(newCar);
        model.addAttribute("addCarSuccess", "Car added successfully");
        model.addAttribute("newCar", new Car());
        model.addAttribute("colors", colorService.getColors());
        return "car-form";
    }

    @GetMapping("/update/{id}")
    public String updateCar(Model model, @PathVariable Long id) {
        Car updatedCar = carService.findById(id);
        model.addAttribute("updatedCar", updatedCar);
        model.addAttribute("colors", colorService.getColors());
        return "car-update";
    }

    @PostMapping("/update/")
    public String updateCar(Model model, @Valid @ModelAttribute("updatedCar") Car updatedCar, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            return "car-update";
        }
        Car existing = carService.findByModel(updatedCar.getModel());
        if (existing != null && !Objects.equals(existing.getId(), updatedCar.getId())) {
            model.addAttribute("updatedCar", updatedCar);
            model.addAttribute("updateCarError", "Car with current model already exists");
            return "car-update";
        }
        carService.saveCar(updatedCar);
        return "redirect:/car/all";
    }
}
