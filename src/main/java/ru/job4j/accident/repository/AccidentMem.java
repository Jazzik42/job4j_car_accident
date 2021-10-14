package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements AccidentDAO {

    private static final AtomicInteger AC_ID = new AtomicInteger(1);
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private final Map<Integer, Rule> rules = new HashMap<>();

    public AccidentMem() {
        Accident accident1 = new Accident();
        Accident accident2 = new Accident();
        accident1.setAddress("address1");
        accident1.setName("name1");
        accident1.setText("text1");
        accident2.setAddress("address2");
        accident2.setName("name2");
        accident2.setText("text2");
        this.saveOrUpdateAccident(accident1);
        this.saveOrUpdateAccident(accident2);
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
        rules.put(1, Rule.of(1, "Статья. 1"));
        rules.put(2, Rule.of(2, "Статья. 2"));
        rules.put(3, Rule.of(3, "Статья. 3"));
    }

    @Override
    public List<Accident> getAccidents() {
        return new ArrayList<>(accidents.values());
    }

    @Override
    public void saveOrUpdateAccident(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(AC_ID.getAndIncrement());
        }
        accidents.put(accident.getId(), accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        if (accidents.containsKey(id)) {
            return Optional.of(accidents.get(id));
        }
        return Optional.empty();
    }

    @Override
    public Optional<AccidentType> findAccidentTypeById(int id) {
        if (types.containsKey(id)) {
            return Optional.of(types.get(id));
        }
        return Optional.empty();
    }

    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return new ArrayList<>(types.values());
    }

    @Override
    public Optional<Rule> findRuleById(int id) {
        if (rules.containsKey(id)) {
            return Optional.of(rules.get(id));
        }
        return Optional.empty();
    }

    @Override
    public List<Rule> findAllRules() {
        return new ArrayList<>(rules.values());
    }
}
