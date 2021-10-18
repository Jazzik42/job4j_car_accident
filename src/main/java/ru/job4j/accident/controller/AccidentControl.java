package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.RuleRepository;
import ru.job4j.accident.repository.TypeRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccidentControl {

    private final AccidentRepository accidentRep;
    private final RuleRepository ruleRep;
    private final TypeRepository typeRep;

    public AccidentControl(AccidentRepository accidentRep, RuleRepository ruleRep,
                           TypeRepository typeRep) {
        this.accidentRep = accidentRep;
        this.ruleRep = ruleRep;
        this.typeRep = typeRep;
    }

    @ModelAttribute("types")
    public List<AccidentType> getTypes() {
        List<AccidentType> types = new ArrayList<>();
        typeRep.findAll().forEach(types::add);
        return types;
    }

    @ModelAttribute("rules")
    public List<Rule> getRules() {
        List<Rule> rules = new ArrayList<>();
        ruleRep.findAll().forEach(rules::add);
        return rules;
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("accident") Accident accident) {
        return "accident/createOrUpdate";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("accident") Accident accident,
                       @RequestParam("type.id") int typeId,
                       @RequestParam("rIds") String[] ruleIds) {
        for (String ruleId: ruleIds) {
            accident.saveRule(ruleRep.findById(Integer.parseInt(ruleId)).get());
        }
        accident.setType(typeRep.findById(typeId).get());
        accidentRep.save(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("accId") int accId, Model model) {
        model.addAttribute("accident", accidentRep.findById(accId).get());
        return "accident/createOrUpdate";
    }
}