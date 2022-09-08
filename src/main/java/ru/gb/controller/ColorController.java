package ru.gb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.entities.Color;
import ru.gb.services.ColorService;

import java.util.List;

@Controller
@RequestMapping("/color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping("/all")
    public String getAllColors(Model model) {
        List<Color> list = colorService.getColors();
        model.addAttribute("colors", list);
        return "all-colors";
    }

}
