package ru.job4j.accident.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;

@Controller
public class IndexControl {

    private AccidentService accidentService;

    public IndexControl(@Qualifier("HIBER") AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentService.findAllAccidents());
        return "index";
    }
}
