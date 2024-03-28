package models.ground;

import common.model.TimeStamped;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "anode")
public class Anode extends TimeStamped {
    public String name;
    public Integer damage;
}
