package models.ground;

import common.model.TimeStamped;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "row")
public class Row extends TimeStamped {
    public String name;
    public Tier tier;

    public Row() {
    }

    public Row(String name, Tier tier) {
        this.tier = tier;
    }

}
