package models.information;

import common.model.information.Event;
import models.ground.Cell;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "vacuuming")
public class Vacuuming extends Event {

    @OneToOne
    public Cell cell;

    public Vacuuming(Cell cell) {
        this.cell = cell;
    }

    public void act(Date acted) {
        this.happened = acted != null ? acted : happened;
    }
}
