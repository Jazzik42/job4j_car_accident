package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.utils.AccidentExtractor;

import java.sql.PreparedStatement;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class AccidentJdbcTemplate implements AccidentDAO {
    private final JdbcTemplate jdbc;
    private final AccidentExtractor accidentExtractor;

    public AccidentJdbcTemplate(JdbcTemplate jdbc, AccidentExtractor accidentExtractor) {
        this.jdbc = jdbc;
        this.accidentExtractor = accidentExtractor;
    }

    @Override
    public List<Accident> findAllAccidents() {
        List<Accident> rsl = jdbc.query("select a.id as a_id, a.name as a_name, "
                        + "a.text as a_text, a.address as a_address, "
                        + "t.id as t_id, t.name as t_name "
                        + " from accident as a left join type as t on a.type_id = t.id "
                        + "left join accident_rule as a_r on a.id = a_r.accident_id "
                        + "left join rule as r on r.id = a_r.rules_id",
                accidentExtractor
        );
        rsl.sort(Comparator.comparing(Accident::getId));
        return rsl;
    }

    @Override
    public Accident saveOrUpdateAccident(Accident accident) {
        if (accident.getId() == 0) {
            return saveAcc(accident);
        }
        return updateAcc(accident);
    }

    private Accident saveAcc(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into accident(name, text, address, type_id) "
                            + "values(?, ?, ?, ?)", new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId((int) keyHolder.getKey());
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rule (accident_id, rule_id) "
                            + "values (?, ?)",
                    accident.getId(),
                    rule.getId());
        }
        return accident;
    }

    private Accident updateAcc(Accident accident) {
        jdbc.update("update accident set "
                        + "name = ?, text = ?, address = ?, type_id = ? "
                        + "where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        jdbc.update("delete from accident_rule "
                        + "where accident_id = ?",
                accident.getId());
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rule (accident_id, rule_id) "
                            + "values (?, ?)",
                    accident.getId(),
                    rule.getId());
        }
        return accident;
    }

    @Override
    public Optional<Accident> findById(int id) {
        List<Accident> list = jdbc.query("select "
                        + "a.id as a_id, a.name as a_name, "
                        + "a.text as a_text, a.address as a_address, a.type_id "
                        + "as t_id, "
                        + "t.name as t_name "
                        + "from accident as a "
                        + "left join type as t "
                        + "on a.type_id = t.id "
                        + "left join accident_rule as a_r on a.id = a_r.accident_id "
                        + "left join rule as r on r.id = a_r.rules_id "
                        + "where a.id = ? ",
                accidentExtractor, id);
        return Optional.of(list.get(0));
    }

    @Override
    public Optional<AccidentType> findAccidentTypeById(int id) {
        return jdbc.query("select * from type where id = ?",
                rs -> {
                    if (!rs.next()) {
                        return Optional.empty();
                    }
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return Optional.of(type);
                }, id);
    }

    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return jdbc.query("select * from type",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

    @Override
    public Optional<Rule> findRuleById(int id) {
        return jdbc.query("select * "
                + "from rule where id = ?", rs -> {
            if (!rs.next()) {
                return Optional.empty();
            }
            Rule rule = new Rule();
            rule.setId(rs.getInt("id"));
            rule.setName(rs.getString("name"));
            return Optional.of(rule);
        }, id);
    }

    @Override
    public List<Rule> findAllRules() {
        return jdbc.query("select * from rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }
}