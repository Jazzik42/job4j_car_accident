package ru.job4j.accident.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Accident {
    private int id;
    private String name;
    private String text;
    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return Objects.equals(name, accident.name) && Objects.equals(text, accident.text) && Objects.equals(address, accident.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, text, address);
    }
}