package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class AccidentJdbcTemplate implements AccidentDAO {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Accident> findAllAccidents() {
        List<Accident> rsl = jdbc.query("select a.id as a_id, a.name as a_name, "
                        + "a.text as a_text, a.address as a_address, "
                        + "t.id as t_id, t.name as t_name "
                        + " from accident as a left join type as t on a.type_id = t.id",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("a_id"));
                    accident.setName(rs.getString("a_name"));
                    accident.setText(rs.getString("a_text"));
                    accident.setAddress(rs.getString("a_address"));
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("t_id"));
                    type.setName(rs.getString("t_name"));
                    accident.setType(type);
                    return accident;
                });
        for (Accident acc : rsl) {
            List<Rule> rules = jdbc.query("select * "
                            + "from accident_rule as a_r "
                            + "left join rule as r on a_r.rule_id = r.id where a_r.accident_id = ?",
                    (rs, row) -> {
                        Rule rule = new Rule();
                        rule.setId(rs.getInt("id"));
                        rule.setName(rs.getString("name"));
                        return rule;
                    }, acc.getId());
            for (Rule rule : rules) {
                acc.saveRule(rule);
            }
        }
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
        Accident accident = jdbc.query("select "
                + "a.id as a_id, a.name as a_name, "
                + "a.text as a_text, a.address as a_address, a.type_id "
                + "as t_id, "
                + "t.name as t_name "
                + "from accident as a "
                + "left join type as t "
                + "on a.type_id = t.id "
                + "where a.id = ?", rs -> {
            if (!rs.next()) {
                return null;
            }
            Accident acc = new Accident();
            acc.setId(rs.getInt("a_id"));
            acc.setName(rs.getString("a_name"));
            acc.setText(rs.getString("a_text"));
            acc.setAddress(rs.getString("a_address"));
            AccidentType type = new AccidentType();
            type.setId(rs.getInt("t_id"));
            type.setName(rs.getString("t_name"));
            acc.setType(type);
            return acc;
        }, id);
        if (accident == null) {
            return Optional.empty();
        }
        List<Rule> rules = jdbc.query("select * "
                        + "from accident_rule as a "
                        + "left join rule as r "
                        + "on a.rule_id = r.id "
                        + "where a.accident_id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, accident.getId());
        for (Rule rule : rules) {
            accident.saveRule(rule);
        }
        return Optional.of(accident);
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