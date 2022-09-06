package ru.gb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.entities.Color;

@Repository
public interface ColorRepository extends CrudRepository<Color, Long> {
}
