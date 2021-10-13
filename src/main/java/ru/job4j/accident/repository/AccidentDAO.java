package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;

import java.util.List;

public interface AccidentDAO {

     List<Accident> getAccidents();

     void saveOrUpdateAccident(Accident accident);

     Accident findById(int id);
}
