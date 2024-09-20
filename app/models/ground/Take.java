package models.ground;

import common.model.TimeStamped;
import models.information.TakeScrubbing;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "take")
public class Take extends TimeStamped {

    public String name;

    @ManyToOne
    public Cell cell;
    public TakeNumber number;
    public Date scrubbed;
    public Date smacked;


    public Take() {
    }

    public Take(Cell cell, TakeNumber takeNumber) {
        this.cell = cell;
        this.number = takeNumber;
    }

    public void clean(Date date) {
        scrubbed = date;
        smacked = date;
        this.save();
        TakeScrubbing scrubbing = new TakeScrubbing(this, date);
        scrubbing.act();
        List<Anode> items = Anode.findByTake(this);
        for (Anode anode : items) {
            anode.clean(date);
        }
    }

    public static Take findByCellAndNumber(Cell cell, TakeNumber takeNumber) {
        return find("cell is ?1 and number is ?2", cell, takeNumber).first();
    }

    public Cell getCell() {
        return this.cell;
    }

    public Integer getQuantity() {
        return this.number.quantity;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
