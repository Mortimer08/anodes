package models.ground;

import common.model.TimeStamped;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "take")
public class Take extends TimeStamped {
    public String name;
    public Take() {
    }
}
