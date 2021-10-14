package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.List;
import java.util.Optional;

public interface AccidentService {

    List<Accident> getAccidents();

    void saveOrUpdateAccident(Accident accident);

    Optional<Accident> findById(int id);

    Optional<AccidentType> findAccidentTypeById(int id);

    List<AccidentType> findAllAccidentTypes();
}
