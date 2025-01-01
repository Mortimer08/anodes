package models.ground;

import common.model.TimeStamped;
import models.Team;
import models.information.Vacuuming;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cell")
public class Cell extends TimeStamped {

    public String name;
    public Integer number;
    public Boolean needRepair;
    public Team team;

    @OneToOne
    public Vacuuming lastVacuuming;
    @ManyToOne
    public Row row;

    public Cell() {
    }

    public Cell(Integer number, Row row) {
        this.number = number;
        this.row = row;
        this.name = row.name.concat(String.valueOf(this.number));
    }

    public static Take findTake(Cell cell, TakeNumber takeNumber) {
        return Take.find("select t from Take t where t.cell=?1 and t.number=?2", cell, takeNumber).first();
    }

    public List<Take> findTakes() {
        return Take.find("select t from Take t where t.cell=?1", this).fetch();
    }

    public static List<Cell> findByRow(Row row) {
        return Cell.find("select c from Cell c where c.row=?1 order by number", row).fetch();
    }

    public void clean(Date date) {
        Vacuuming vacuuming = new Vacuuming();
        vacuuming.happened = date;
        vacuuming.save();
        this.lastVacuuming = vacuuming;
        this.save();
    }

    public static List<Cell> findByTeam(final Team team) {
        return find("select c from Cell c where c.team = ?1 order by c.row asc, c.number asc", team).fetch();
    }

    public void setTeam(final Team team) {
        this.team = team;
        this.save();
    }

    public Vacuuming getLastVacuuming() {
        if (lastVacuuming == null) {
            lastVacuuming = Vacuuming.findLast(this);
            save();
        }
        return lastVacuuming;
    }

    @Override
    public String toString() {
        return name;
    }
}
