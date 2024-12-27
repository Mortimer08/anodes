package common.model.information;

import common.model.TimeStamped;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class Event extends TimeStamped implements EventInterface {

    @Column
    @Lob
    public String comment;
    @Column
    public Date happened;

    @Override
    public void act() {
        this.happened = new Date();
        this.save();
    }

    @Override
    public Date getHappened() {
        return this.happened;
    }


}
