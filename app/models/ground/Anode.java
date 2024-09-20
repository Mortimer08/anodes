package models.ground;

import common.model.TimeStamped;
import models.information.AnodeScrubbing;
import models.information.AnodeSmack;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "anode")
public class Anode extends TimeStamped {

    public Integer number;

    @ManyToOne
    public Take take;
    public String name;
    public Integer weight;
    public Integer damage;
    public Date scrubbed;
    public Date smacked;

    public Cell getCell() {
        return this.take.getCell();
    }

    public void clean(Date date) {
        scrubbed = date;
        smacked = date;
        this.save();
        AnodeScrubbing anodeScrubbing = new AnodeScrubbing(this);
        anodeScrubbing.act();
        AnodeSmack anodeSmack = new AnodeSmack(this, anodeScrubbing);
        anodeSmack.act();
    }

    public static List<Anode> findByTake(Take take) {
        return Anode.find("select a from Anode a where a.take=?1", take).fetch();
    }

    public Integer getDamage() {
        return damage;
    }
}
