package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements AccidentDAO {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private AtomicInteger id = new AtomicInteger(1);

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
    }

    @Override
    public List<Accident> getAccidents() {
        return new ArrayList<>(accidents.values());
    }

    @Override
    public void saveOrUpdateAccident(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(id.getAndIncrement());
        }
        accidents.put(accident.getId(), accident);
    }

    @Override
    public Accident getAccident(int id) {
        return accidents.get(id);
    }
}
