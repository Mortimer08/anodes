package common.model.information;

import common.model.TimeStamped;
import models.auth.User;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class Event extends TimeStamped implements EventInterface {

    @Column
    @Lob
    public String comment;
    @Column
    public Date happened;
    @ManyToOne
    public User creator;

    @Override
    public void act() {
        this.save();
    }

    @Override
    public Date getHappened() {
        return this.happened;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
