package controllers.information.repo;

import play.db.jpa.JPA;

import javax.persistence.Tuple;
import java.util.List;

public class TotalRepo {

    public static List<Tuple> findResults() {
        final String query = "select DATEDIFF(NOW(), MIN(s.happened)) takeTerm, " +
                "DATEDIFF(NOW(), MIN(v.happened)) cellTerm, " +
                "SUM(s.firstDamage) firstDamage, " +
                "SUM(s.toChange) toChange, " +
                "SUM(s.firstDamage) + SUM(s.toChange) totalDamage, " +
                "c.team team " +
                "from take t " +
                "join take_scrubbing s " +
                "on t.lastScrubbing_id = s.id " +
                "left join cell c " +
                "on t.cell_id = c.id " +
                "join vacuuming v " +
                "on c.lastVacuuming_id = v.id " +
                "group by c.team " +
                "order by c.team";
        return  JPA.em().createNativeQuery(query, Tuple.class)
                .getResultList();
    }

    public static Tuple findTotal() {
        final String query = "select DATEDIFF(NOW(), MIN(s.happened)) takeTerm, " +
                "DATEDIFF(NOW(), MIN(v.happened)) cellTerm, " +
                "SUM(s.firstDamage) firstDamage, " +
                "SUM(s.toChange) toChange, " +
                "SUM(s.firstDamage) + SUM(s.toChange) totalDamage " +
                "from take t " +
                "join take_scrubbing s " +
                "on t.lastScrubbing_id = s.id " +
                "left join cell c " +
                "on t.cell_id = c.id " +
                "join vacuuming v " +
                "on c.lastVacuuming_id = v.id " +
                "order by c.team";
        return (Tuple) JPA.em().createNativeQuery(query, Tuple.class)
                .getSingleResult();
    }
}

