package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.List;
import java.util.Optional;

@Service("JDBC")
public class AccidentServiceJdbcTemplate implements AccidentService {
    private AccidentJdbcTemplate accJdbc;

    @Autowired
    public AccidentServiceJdbcTemplate(AccidentJdbcTemplate accJdbc) {
        this.accJdbc = accJdbc;
    }

    @Override
    public List<Accident> findAllAccidents() {
        return accJdbc.findAllAccidents();
    }

    @Override
    public Accident saveOrUpdateAccident(Accident accident) {
        accJdbc.saveOrUpdateAccident(accident);
        return accident;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accJdbc.findById(id);
    }

    @Override
    public Optional<AccidentType> findAccidentTypeById(int id) {
        return accJdbc.findAccidentTypeById(id);
    }

    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return accJdbc.findAllAccidentTypes();
    }

    @Override
    public Optional<Rule> findRuleById(int id) {
        return accJdbc.findRuleById(id);
    }

    @Override
    public List<Rule> findAllRules() {
        return accJdbc.findAllRules();
    }
}
