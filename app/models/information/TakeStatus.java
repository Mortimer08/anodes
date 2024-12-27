package models.information;

import models.ground.Take;

import java.util.Date;

public class TakeStatus {

    public Take take;
    public Date scrubbed;
    public int term;
    public String comment;
    public int firstDamage;

    public TakeStatus(Take take) {
        this.take = take;
    }

    public void setScrubbed(Date scrubbed) {
        this.scrubbed = scrubbed;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setFirstDamage(int firstDamage) {
        this.firstDamage = firstDamage;
    }
}
