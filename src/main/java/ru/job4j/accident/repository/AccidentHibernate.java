package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Optional;

@Repository
public class AccidentHibernate implements AccidentDAO {
    private final SessionFactory sf;

    @Autowired
    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public List<Accident> findAllAccidents() {
        List<Accident> list;
        Session session = sf.getCurrentSession();
        list = session.createQuery("from Accident", Accident.class).list();
        return list;
    }

    @Override
    public Accident saveOrUpdateAccident(Accident accident) {
        Session session = sf.getCurrentSession();
        session.saveOrUpdate(accident);
        return accident;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.of(sf.getCurrentSession().get(Accident.class, id));
    }

    @Override
    public Optional<AccidentType> findAccidentTypeById(int id) {
        return Optional.of(sf.getCurrentSession().get(AccidentType.class, id));
    }

    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return sf.getCurrentSession().createQuery(
                "from AccidentType", AccidentType.class).list();
    }

    @Override
    public Optional<Rule> findRuleById(int id) {
        return Optional.of(sf.getCurrentSession().get(Rule.class, id));
    }

    @Override
    public List<Rule> findAllRules() {
        return sf.getCurrentSession().createQuery(
                "from Rule", Rule.class).list();
    }
}

