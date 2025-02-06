package models.information;

import common.model.information.Event;
import controllers.auth.Secure;
import controllers.information.dto.ScrubbingFilter;
import controllers.util.DateUtils;
import models.ground.Take;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "take_scrubbing")
public class TakeScrubbing extends Event {

    @ManyToOne
    public Take take;

    public Integer machined = 0;
    public Integer handled = 0;
    public Integer firstDamage = 0;
    public Integer toChange = 0;
    public Integer changed = 0;

    public TakeScrubbing() {
        this.creator = Secure.getUser();
    }

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
        take.lastScrubbing = this;
    }

    public static TakeScrubbing findLast(final Take take) {
        return find(" select s from TakeScrubbing s where s.take = ?1 order by happened desc", take).first();
    }

    public static List<Tuple> findByFilter(final ScrubbingFilter f) {
        if (f.teamIndex == null) {
            f.teamIndex = new ArrayList<>();
        }
        final Date begin = DateUtils.plusDays(f.begin, -1);
        final Date end = DateUtils.plusDays(f.end, 1);
        final String query = String.format("select " +
                "s.id as id, s.happened as happened, t.name as take, s.comment as comment, " +
                "s.firstDamage as firstDamage, s.toChange as toChange, " +
                "s.machined as machined, s.handled as handled," +
                "s.changed as changed, " +
                "t.number as number " +
                "from take_scrubbing s left join take t on s.take_id = t.id " +
                "left join cell c on t.cell_id = c.id where " +
                "c.team in ?1 and happened > ?2 and happened < ?3 " +
                "order by %s %s", f.sort, f.order);
        return JPA.em().createNativeQuery(query, Tuple.class)
                .setParameter(1, f.teamIndex)
                .setParameter(2, begin)
                .setParameter(3, end)
                .getResultList();
    }

    private TakeScrubbing findClosest() {
        return find("take = ?1 and created < ?2 order by created desc", this.take, this.created).first();
    }

    public void remove() {
        final Take take = this.take;
        take.lastScrubbing = findClosest();
        take.save();
        this.delete();
    }

}