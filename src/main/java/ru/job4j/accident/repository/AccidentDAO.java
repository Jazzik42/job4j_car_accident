package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Optional;

public interface AccidentDAO {

     List<Accident> findAllAccidents();

     Accident saveOrUpdateAccident(Accident accident);

     Optional<Accident> findById(int id);

     Optional<AccidentType> findAccidentTypeById(int id);

     List<AccidentType> findAllAccidentTypes();

     Optional<Rule> findRuleById(int id);

     List<Rule> findAllRules();
}
