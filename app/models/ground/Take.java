package models.ground;

import common.model.TimeStamped;
import models.information.TakeScrubbing;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "take")
public class Take extends TimeStamped {

    public String name;

    @ManyToOne
    public Cell cell;
    public TakeNumber number;
    @OneToOne
    public TakeScrubbing lastScrubbing;


    public Take() {
    }

    public Take(Cell cell, TakeNumber takeNumber) {
        this.cell = cell;
        this.number = takeNumber;
    }

    public void clean(Date date) {
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

    public static List<Take> findByCell(final Cell cell) {
        return find("cell is ?1 order by number asc", cell).fetch();
    }

    public Cell getCell() {
        return this.cell;
    }

    public Integer getQuantity() {
        return this.number.quantity;
    }

    public TakeScrubbing getLastScrubbing() {
        if (lastScrubbing == null) {
            lastScrubbing = TakeScrubbing.findLast(this);
            save();
        }
        return lastScrubbing;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
