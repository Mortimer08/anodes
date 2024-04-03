package models.ground;

import common.model.TimeStamped;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "anode")
public class Anode extends TimeStamped {
    public Integer number;
    @ManyToOne
    public Take take;
    public String name;
    public Integer damage;

    public Cell getCell() {
        return this.take.getCell();
    }
}
