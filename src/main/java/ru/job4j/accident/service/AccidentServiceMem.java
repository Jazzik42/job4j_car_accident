package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;
import java.util.Optional;

@Service
public class AccidentServiceMem implements AccidentService {
    private AccidentMem accidentMem;

    @Autowired
    public AccidentServiceMem(AccidentMem accDao) {
        this.accidentMem = accDao;
    }

    @Override
    public List<Accident> findAllAccidents() {
        return accidentMem.findAllAccidents();
    }

    @Override
    public Accident saveOrUpdateAccident(Accident accident) {
        accidentMem.saveOrUpdateAccident(accident);
        return accident;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    @Override
    public Optional<AccidentType> findAccidentTypeById(int id) {
        return accidentMem.findAccidentTypeById(id);
    }

    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return accidentMem.findAllAccidentTypes();
    }

    @Override
    public Optional<Rule> findRuleById(int id) {
        return accidentMem.findRuleById(id);
    }

    @Override
    public List<Rule> findAllRules() {
        return accidentMem.findAllRules();
    }
}
