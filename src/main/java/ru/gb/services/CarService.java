package ru.gb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.entities.Car;
import ru.gb.repositories.CarRepository;
import ru.gb.repositories.ColorRepository;

import java.util.Comparator;
import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    private ColorRepository colorRepository;

    @Autowired
    public void setColorRepository(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<Car> findAllCars() {
        List<Car> list = (List<Car>) carRepository.findAll();
        list.sort(Comparator.comparing(Car::getId));
        return list;
    }

    public void saveCar(Car car) {
        carRepository.save(car);
    }

    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));
    }

    public Car findByModel(String model) {
        return carRepository.findCarByModel(model);
    }


    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }
}
