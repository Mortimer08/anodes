package models.information;

import models.ground.Take;

import java.util.Date;

public class TakeDetail {

    public Take take;
    public Date scrubbed;
    public Integer term;
    public String comment;
    public Integer machined;
    public Integer handled;
    public Integer firstDamage;
    public Integer toChange;
    public Integer changed;
    public Boolean checked = false;
    public Date date;

    public TakeDetail(Take take) {
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

    public void setFirstDamage(Integer firstDamage) {
        this.firstDamage = firstDamage;
    }

    public boolean isScrubbed() {
        return this.take != null && this.checked;
    }
}
