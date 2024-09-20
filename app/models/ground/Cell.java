package models.ground;

import common.model.TimeStamped;
import models.information.Vacuuming;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cell")
public class Cell extends TimeStamped {

    public String name;
    public Integer number;
    public Boolean needRepair;
    public Date cleaned;

    @ManyToOne
    public Row row;

    public Cell() {
    }

    public Cell(Integer number, Row row) {
        this.number = number;
        this.row = row;
        this.name = row.name.concat(String.valueOf(this.number));
    }

    public static List<Cell> findByRow(Row row) {
        return Cell.find("select c from Cell c where c.row=?1 order by number", row).fetch();
    }

    public void clean(Date date){
        Vacuuming vacuuming = new Vacuuming(this);
        vacuuming.act();
        vacuuming.save();
        this.cleaned = date;
        this.save();
    }

    @Override
    public String toString() {
        return name;
    }
}
