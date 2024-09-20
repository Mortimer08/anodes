package models.information;

import common.model.information.Event;
import models.ground.Anode;
import models.ground.Take;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "anode_smack")
public class AnodeSmack extends Event {

    @OneToOne
    public Anode anode;
    @OneToOne
    public AnodeScrubbing anodeScrubbing;

    public Boolean machined = true;
    public Integer damage;

    public AnodeSmack(Anode anode, AnodeScrubbing anodeScrubbing) {
        this.anode = anode;
        this.anodeScrubbing = anodeScrubbing;
    }

}
