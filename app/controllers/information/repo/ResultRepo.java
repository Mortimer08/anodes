package controllers.information.repo;

import models.Team;
import play.db.jpa.JPA;

import java.util.Date;

public class ResultRepo {

    private static Integer countVacuuming(final Date begin, final Date end, final Team team) {
        final String query = "select count(v.happened) " +
                "from cell c " +
                "join vacuuming v on c.lastVacuuming_id = v.id " +
                "where v.happened > ?1 " +
                "and v.happened < ?2 " +
                "and c.team = ?3";
        final Number result = (Number) JPA.em().createNativeQuery(query)
                .setParameter(1, begin)
                .setParameter(2, end)
                .setParameter(3, team.ordinal())
                .getSingleResult();
        return result != null ? result.intValue() : 0;
    }

    private static Integer countMachined(final Date begin, final Date end, final Team team) {
        final String query = "select sum(s.machined) " +
                "from take t " +
                "join cell c on t.cell_id = c.id " +
                "join take_scrubbing s on t.lastScrubbing_id = s.id " +
                "where s.happened > ?1 " +
                "and s.happened < ?2 " +
                "and c.team = ?3";
        final Number result = (Number) JPA.em().createNativeQuery(query)
                .setParameter(1, begin)
                .setParameter(2, end)
                .setParameter(3, team.ordinal())
                .getSingleResult();
        return result != null ? result.intValue() : 0;
    }

    private static Integer countHandled(final Date begin, final Date end, final Team team) {
        final String query = "select sum(s.handled) " +
                "from take t " +
                "join cell c on t.cell_id = c.id " +
                "join take_scrubbing s on t.lastScrubbing_id = s.id " +
                "where s.happened > ?1 " +
                "and s.happened < ?2 " +
                "and c.team = ?3";
        final Number result = (Number) JPA.em().createNativeQuery(query)
                .setParameter(1, begin)
                .setParameter(2, end)
                .setParameter(3, team.ordinal())
                .getSingleResult();
        return result != null ? result.intValue() : 0;
    }

    private static Integer countChanged(final Date begin, final Date end, final Team team) {
        final String query = "select sum(s.changed) " +
                "from take t " +
                "join cell c on t.cell_id = c.id " +
                "join take_scrubbing s on t.lastScrubbing_id = s.id " +
                "where s.happened > ?1 " +
                "and s.happened < ?2 " +
                "and c.team = ?3";
        final Number result = (Number) JPA.em().createNativeQuery(query)
                .setParameter(1, begin)
                .setParameter(2, end)
                .setParameter(3, team.ordinal())
                .getSingleResult();
        return result != null ? result.intValue() : 0;
    }

    public static Integer[] countVacuuming(final Date begin, final Date end) {
        Integer[] vacuumings = new Integer[Team.values().length];
        for (Team team : Team.values()) {
            vacuumings[team.ordinal()] = countVacuuming(begin, end, team);
        }
        return vacuumings;
    }

    public static Integer[] countMachined(final Date begin, final Date end) {
        Integer[] machined = new Integer[Team.values().length];
        for (Team team : Team.values()) {
            machined[team.ordinal()] = countMachined(begin, end, team);
        }
        return machined;
    }

    public static Integer[] countHandled(final Date begin, final Date end) {
        Integer[] handled = new Integer[Team.values().length];
        for (Team team : Team.values()) {
            handled[team.ordinal()] = countHandled(begin, end, team);
        }
        return handled;
    }

    public static Integer[] countChanged(final Date begin, final Date end) {
        Integer[] changed = new Integer[Team.values().length];
        for (Team team : Team.values()) {
            changed[team.ordinal()] = countChanged(begin, end, team);
        }
        return changed;
    }
}
