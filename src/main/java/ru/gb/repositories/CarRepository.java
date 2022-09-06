package ru.gb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.entities.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    Car findCarByModel(String model);
}
