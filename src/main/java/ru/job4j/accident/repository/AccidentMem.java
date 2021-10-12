package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccidentMem implements AccidentDAO {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private int id;

    public List<Accident> getAccidents() {
        return (List) accidents.values();
    }

    public void addAccident(Accident accident) {
        accidents.put(++id, accident);
        accident.setId(id);
    }
}
