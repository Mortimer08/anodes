package models.ground;

import common.model.TimeStamped;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "take")
public class Take extends TimeStamped {
    public String name;
    @ManyToOne
    public Cell cell;
    public TakeNumber number;

    public Take() {
    }

    public static Take findByCellAndNumber(Cell cell, TakeNumber takeNumber) {
        return find("cell is ?1 and number is ?2", cell, takeNumber).first();
    }

    public Cell getCell() {
        return this.cell;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
