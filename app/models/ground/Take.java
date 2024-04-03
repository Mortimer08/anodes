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

    @Override
    public String toString() {
        return this.name;
    }
}
