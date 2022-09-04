package ru.gb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.entities.Color;
import ru.gb.repositories.ColorRepository;

import java.util.List;

@Service
public class ColorService {

    private ColorRepository colorRepository;

    @Autowired
    public void setColorRepository(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<Color> getColors() {
        return (List<Color>) colorRepository.findAll();
    }

    public Color findById(Long id) {
        return colorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));
    }

    public Color saveColor(Color color) {
       return colorRepository.save(color);
    }
}
