package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentDAO;

import java.util.List;

@Service
public class AccidentServiceMem implements AccidentService {
    private AccidentDAO accidentDAO;

    @Autowired
    public AccidentServiceMem(AccidentDAO accDao) {
        this.accidentDAO = accDao;
    }

    @Override
    public List<Accident> getAccidents() {
        return accidentDAO.getAccidents();
    }

    @Override
    public void saveOrUpdateAccident(Accident accident) {
        accidentDAO.saveOrUpdateAccident(accident);
    }

    @Override
    public Accident getAccident(int id) {
        return accidentDAO.getAccident(id);
    }
}
