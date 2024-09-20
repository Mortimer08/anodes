package models.information;

import common.model.information.Event;
import models.ground.Anode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "anode_scrubbing")
public class AnodeScrubbing extends Event {

    @OneToOne
    public Anode anode;

    public Boolean machined = true;
    public Integer damage;

    public AnodeScrubbing(Anode anode) {
        this.anode = anode;
        this.damage = anode.getDamage();
    }

    public void byHand() {
        machined = false;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

}
