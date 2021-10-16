package ru.job4j.accident.utils;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccidentExtractor implements ResultSetExtractor<List<Accident>> {

    @Override
    public List<Accident> extractData(ResultSet resultSet)
            throws SQLException, DataAccessException {
        List<Accident> data = new ArrayList<>();
        while (resultSet.next()) {
            Accident accident = new Accident();
            accident.setId(resultSet.getInt("a_id"));
            accident.setName(resultSet.getString("a_name"));
            accident.setText(resultSet.getString("a_text"));
            accident.setAddress(resultSet.getString("a_address"));
            AccidentType type = new AccidentType();
            type.setId(resultSet.getInt("t_id"));
            type.setName(resultSet.getString("t_name"));
            accident.setType(type);
            data.add(accident);
        }
        return data;
    }
}
