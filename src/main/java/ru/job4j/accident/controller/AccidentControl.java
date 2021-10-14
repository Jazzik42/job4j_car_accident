package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.service.AccidentService;

import java.util.List;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @ModelAttribute("types")
    public List<AccidentType> getTypes() {
        return accidentService.findAllAccidentTypes();
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("accident") Accident accident) {
        return "accident/createOrUpdate";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("accident") Accident accident,
                       @RequestParam("type.id") int id) {
        accident.setType(accidentService.findAccidentTypeById(id).get());
        accidentService.saveOrUpdateAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("accId") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id).get());
        return "accident/createOrUpdate";
    }
}