package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.List;
import java.util.Optional;

@Service("HIBER")
public class AccidentServiceHibernate implements AccidentService {
    private AccidentHibernate accidentHibernate;

    @Autowired
    public AccidentServiceHibernate(AccidentHibernate accHb) {
        this.accidentHibernate = accHb;
    }

    @Override
    @Transactional
    public List<Accident> findAllAccidents() {
        return accidentHibernate.findAllAccidents();
    }

    @Override
    @Transactional
    public Accident saveOrUpdateAccident(Accident accident) {
        return accidentHibernate.saveOrUpdateAccident(accident);
    }

    @Override
    @Transactional
    public Optional<Accident> findById(int id) {
        return accidentHibernate.findById(id);
    }

    @Override
    @Transactional
    public Optional<AccidentType> findAccidentTypeById(int id) {
        return accidentHibernate.findAccidentTypeById(id);
    }

    @Override
    @Transactional
    public List<AccidentType> findAllAccidentTypes() {
        return accidentHibernate.findAllAccidentTypes();
    }

    @Override
    @Transactional
    public Optional<Rule> findRuleById(int id) {
        return accidentHibernate.findRuleById(id);
    }

    @Override
    @Transactional
    public List<Rule> findAllRules() {
        return accidentHibernate.findAllRules();
    }
}
