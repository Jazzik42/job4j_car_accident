package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;

import java.util.List;

public interface AccidentService {

    List<Accident> getAccidents();

    void addAccident(Accident accident);
}
