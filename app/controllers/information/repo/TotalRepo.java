package controllers.information.repo;

import models.Team;
import play.db.jpa.JPA;

import javax.persistence.Tuple;
import java.util.Date;

public class TotalRepo {

    public static Integer findCellTerm(final Team team) {
        final String query = "select " +
                "DATEDIFF(NOW(), MIN(v.happened)) cellTerm " +
                "from cell c " +
                "join vacuuming v on c.lastVacuuming_id = v.id " +
                "where c.team = ?1";
        final Number result = (Number) JPA.em().createNativeQuery(query)
                .setParameter(1, team.ordinal())
                .getSingleResult();
        return result != null ? result.intValue() : 0;
    }

    public static Integer findCellMaxTerm() {
        final String query = "select " +
                "DATEDIFF(NOW(), MIN(v.happened)) cellMaxTerm " +
                "from cell c " +
                "join vacuuming v " +
                "on c.lastVacuuming_id = v.id ";
        final Number result = (Number) JPA.em().createNativeQuery(query)
                .getSingleResult();
        return result != null ? result.intValue() : 0;
    }

    public static Integer findTakeTerm(final Team team) {
        final String query = "select " +
                "DATEDIFF(NOW(), MIN(s.happened)) takeTerm " +
                "from take t " +
                "left join cell c on t.cell_id = c.id " +
                "join take_scrubbing s on t.lastScrubbing_id = s.id " +
                "where c.team = ?1";
        final Number result = (Number) JPA.em().createNativeQuery(query)
                .setParameter(1, team.ordinal())
                .getSingleResult();
        return result != null ? result.intValue() : 0;
    }

    public static Integer findTakeMaxTerm() {
        final String query = "select " +
                "DATEDIFF(NOW(), MIN(s.happened)) takeMaxTerm " +
                "from take t " +
                "join take_scrubbing s on t.lastScrubbing_id = s.id ";
        final Number result = (Number) JPA.em().createNativeQuery(query)
                .getSingleResult();
        return result != null ? result.intValue() : 0;
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

    public static Tuple findTeamResult(final Team team, final Date begin, final Date end) {
        final String query = "select " +
                "SUM(s.firstDamage) firstDamage, " +
                "SUM(s.toChange) toChange, " +
                "SUM(s.firstDamage) + SUM(s.toChange) totalDamage " +
                "from take t " +
                "join cell c on t.cell_id = c.id " +
                "join take_scrubbing s on t.lastScrubbing_id = s.id " +
                "where s.happened > ?1 " +
                "and s.happened < ?2 " +
                "and c.team = ?3";
        return (Tuple) JPA.em().createNativeQuery(query, Tuple.class)
                .setParameter(1, begin)
                .setParameter(2, end)
                .setParameter(3, team.ordinal())
                .getSingleResult();
    }
}

