package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Arrays;
import java.util.List;

@Controller
public class AccidentControl {
    private final AccidentMem accidents;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @ModelAttribute("types")
    public List<AccidentType> getTypes() {
        return Arrays.asList(AccidentType.of(1, "Две машины"),
                AccidentType.of(2, "Машина и человек"),
                AccidentType.of(3, "Машина и велосипед"));
    }

    @GetMapping("/create")
    public String create(Model model) {
        Accident accident = new Accident();
        model.addAttribute("accident", accident);
        return "accident/createOrUpdate";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("accident") Accident accident,
                       @RequestParam("type.id") int id) {
        List<AccidentType> typesList = getTypes();
        AccidentType accidentType = null;
        for (AccidentType aT: typesList) {
            if (aT.getId() == id) {
                accidentType = aT;
            }
        }
        accident.setType(accidentType);
        accidents.saveOrUpdateAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("accId") int id, Model model) {
        model.addAttribute("accident", accidents.findById(id));
        return "accident/createOrUpdate";
    }
}