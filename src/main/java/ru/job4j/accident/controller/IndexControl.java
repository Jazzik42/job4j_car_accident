package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    private AccidentService accidentService;

    public IndexControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        Accident accident1 = new Accident();
        Accident accident2 = new Accident();
        accident1.setAddress("address1");
        accident1.setName("name1");
        accident1.setText("text1");
        accident2.setAddress("address2");
        accident2.setName("name2");
        accident2.setText("text2");
        accidentService.addAccident(accident1);
        accidentService.addAccident(accident2);
        model.addAttribute("accidents", List.of(accident1, accident2));
        return "index";
    }
}
