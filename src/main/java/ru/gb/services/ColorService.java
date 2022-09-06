package ru.gb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.entities.Color;
import ru.gb.repositories.ColorRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ColorService {

    private ColorRepository colorRepository;

    @Autowired
    public void setColorRepository(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public Color makeColor(String color) {
        return new Color(color);
    }

    public List<Color> getColors() {
        List<Color> list = (List<Color>) colorRepository.findAll();
        list.sort(Comparator.comparing(Color::getId));
        return list;
    }

    public Color findById(Long id) {
        return colorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));
    }

    public Color saveColor(Color color) {
       return colorRepository.save(color);
    }

    public void deleteColor(Color color) {
        colorRepository.delete(color);
    }
}
