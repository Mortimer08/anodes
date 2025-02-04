package controllers.information.repo;

import play.db.jpa.JPA;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

public class TotalRepo {

    public static List<Tuple> findTakeResults(final Date begin, final Date end) {
        final String query = "select " +
                "SUM(s.firstDamage) firstDamage, " +
                "SUM(s.toChange) toChange, " +
                "SUM(s.firstDamage) + SUM(s.toChange) totalDamage, " +
                "c.team team " +
                "from take t " +
                "join take_scrubbing s " +
                "on t.lastScrubbing_id = s.id " +
                "left join cell c " +
                "on t.cell_id = c.id " +
                "where s.happened > ?1 " +
                "and s.happened < ?2 " +
                "group by c.team " +
                "order by c.team";
        return JPA.em().createNativeQuery(query, Tuple.class)
                .setParameter(1, begin)
                .setParameter(2, end)
                .getResultList();
    }

    public static List<Tuple> findCellTerms() {
        final String query = "select " +
                "DATEDIFF(NOW(), MIN(v.happened)) cellTerm, " +
                "c.team team " +
                "from cell c " +
                "join vacuuming v " +
                "on c.lastVacuuming_id = v.id " +
                "group by c.team " +
                "order by c.team";
        return JPA.em().createNativeQuery(query, Tuple.class)
                .getResultList();
    }

    public static Integer findCellMaxTerm() {
        final String query = "select " +
                "DATEDIFF(NOW(), MIN(v.happened)) cellMaxTerm " +
                "from cell c " +
                "join vacuuming v " +
                "on c.lastVacuuming_id = v.id ";
        return ((Number) JPA.em().createNativeQuery(query)
                .getSingleResult()).intValue();
    }

    public static List<Tuple> findTakeTerms() {
        final String query = "select " +
                "DATEDIFF(NOW(), MIN(s.happened)) takeTerm, " +
                "c.team team " +
                "from take t " +
                "left join cell c " +
                "on t.cell_id = c.id " +
                "join take_scrubbing s " +
                "on t.lastScrubbing_id = s.id " +
                "group by c.team " +
                "order by c.team";
        return JPA.em().createNativeQuery(query, Tuple.class)
                .getResultList();
    }

    public static Integer findTakeMaxTerm() {
        final String query = "select " +
                "DATEDIFF(NOW(), MIN(s.happened)) takeMaxTerm " +
                "from take t " +
                "join take_scrubbing s " +
                "on t.lastScrubbing_id = s.id ";
        return ((Number) JPA.em().createNativeQuery(query)
                .getSingleResult()).intValue();
    }

    public static Tuple findTakeTotal(final Date begin, final Date end) {
        final String query = "select " +
                "SUM(s.firstDamage) firstDamage, " +
                "SUM(s.toChange) toChange, " +
                "SUM(s.firstDamage) + SUM(s.toChange) totalDamage " +
                "from take t " +
                "join take_scrubbing s " +
                "on t.lastScrubbing_id = s.id " +
                "where s.happened > ?1 " +
                "and s.happened < ?2 ";
        return (Tuple) JPA.em().createNativeQuery(query, Tuple.class)
                .setParameter(1, begin)
                .setParameter(2, end)
                .getSingleResult();
    }
}

