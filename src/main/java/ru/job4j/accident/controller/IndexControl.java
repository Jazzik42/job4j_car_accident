package ru.job4j.accident.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;


import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {

    private final AccidentRepository accidentRep;

    public IndexControl(AccidentRepository accidentRep) {
        this.accidentRep = accidentRep;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<Accident> res = new ArrayList<>();
        accidentRep.findAll().forEach(res::add);
        model.addAttribute("accidents", res);
        return "index";
    }
}
