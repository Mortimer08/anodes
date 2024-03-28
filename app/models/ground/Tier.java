package models.ground;

import common.model.TimeStamped;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tier")
public class Tier extends TimeStamped {
    public String name;
    public Tier() {
    }
    @Override
    public String toString() {
        return name;
    }
}
