package models.ground;

import common.model.TimeStamped;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cell")
public class Cell extends TimeStamped {
    public String name;
    public Integer number;
    public Row row;

    public Cell() {
    }

    public Cell(Integer number, Row row) {
        this.number = number;
        this.row = row;
        this.name = row.name.concat(String.valueOf(this.number));
    }

    @Override
    public String toString() {
        return name;
    }
}
