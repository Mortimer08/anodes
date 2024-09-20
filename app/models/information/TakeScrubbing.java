package models.information;

import common.model.information.Event;
import models.ground.Take;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "take_scrubbing")
public class TakeScrubbing extends Event {

    @OneToOne
    public Take take;

    public Integer machined;
    public Integer firstDamage;
    public Integer toChange;
    public Integer changed;

    public TakeScrubbing(Take take, Date date) {
        this.happened = date;
        this.take = take;
        this.machined = this.take.getQuantity();
        this.firstDamage = 0;
        this.toChange = 0;
        this.changed = 0;
    }

    public void act(Date acted) {
        this.happened = acted != null ? acted : happened;
    }

}