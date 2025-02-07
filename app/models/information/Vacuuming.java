package models.information;

import common.model.information.Event;
import controllers.auth.Secure;
import controllers.information.dto.VacuumingFilter;
import controllers.util.DateUtils;
import models.ground.Cell;
import play.db.jpa.JPA;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vacuuming")
public class Vacuuming extends Event {

    @OneToOne
    public Cell cell;

    public Vacuuming() {
        this.creator = Secure.getUser();
    }

    public Vacuuming(Cell cell, Date happened) {
        this.cell = cell;
    }

    public void act(Date acted) {
        this.happened = acted != null ? acted : happened;
        cell.lastVacuuming = this;
    }

    public static Vacuuming findLast(final Cell cell) {
        return find(" select v from Vacuuming v where v.cell = ?1 order by happened desc", cell).first();
    }

    public static List<Tuple> findByFilter(VacuumingFilter f) {
        if (f.teamIndex == null) {
            f.teamIndex = new ArrayList<>();
        }
        final Date begin = DateUtils.plusDays(f.begin, -1);
        final Date end = DateUtils.plusDays(f.end, 1);
        final String query = String.format("select " +
                "v.id as id, v.happened as happened, c.name as cell, v.comment as comment " +
                "from vacuuming v left join cell c on v.cell_id = c.id where " +
                "c.team in ?1 and happened > ?2 and happened < ?3 " +
                "order by %s %s", f.sort, f.order);
        return JPA.em().createNativeQuery(query, Tuple.class)
                .setParameter(1, f.teamIndex)
                .setParameter(2, begin)
                .setParameter(3, end)
                .getResultList();
    }

    private Vacuuming findClosest() {
        return find("cell = ?1 and created < ?2 order by created desc", this.cell, this.created).first();
    }

    public void remove() {
        final Cell cell = this.cell;
        cell.lastVacuuming = findClosest();
        cell.save();
        this.delete();
    }
}
