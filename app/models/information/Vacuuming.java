package models.information;

import common.model.information.Event;
import models.Team;
import models.ground.Cell;
import play.db.jpa.JPA;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vacuuming")
public class Vacuuming extends Event {

    @OneToOne
    public Cell cell;

    public Vacuuming() {
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

    public static List<Tuple> findByFilter(List<Integer> teamIndexes, Date begin, Date end) {
        if (teamIndexes == null) {
            teamIndexes = List.of(Team.values().length);
        }

        return JPA.em().createNativeQuery("select " +
                        "v.id as id, v.happened as happened, c.name as cell, v.comment as comment " +
                        "from vacuuming v left join cell c on v.cell_id = c.id where " +
                        "c.team in ?1 and v.happened > ?2 and v.happened < ?3", Tuple.class)
                .setParameter(1, teamIndexes)
                .setParameter(2, begin)
                .setParameter(3, end)
                .getResultList();
    }


}
