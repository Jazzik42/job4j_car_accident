package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

@Controller
public class AccidentControl {
    private final AccidentMem accidents;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        Accident accident = new Accident();
        model.addAttribute("accident", accident);
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("accident") Accident accident) {
        accidents.saveOrUpdateAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("accId") int id, Model model) {
        model.addAttribute("accident", accidents.findById(id));
        return "accident/create";
    }
}