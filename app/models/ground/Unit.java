package models.ground;

import models.Team;
import models.information.CellStatus;
import models.information.TakeScrubbing;
import models.information.TakeStatus;
import models.information.Vacuuming;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class Unit {

    public CellStatus cellStatus;
    public List<TakeStatus> takeStatuses;

    public Unit(CellStatus cellStatus, List<TakeStatus> takeStatuses) {
        this.cellStatus = cellStatus;
        this.takeStatuses = takeStatuses;
    }

    public static List<Unit> findByTeam(final Team team) {
        final List<Cell> cells = Cell.findByTeam(team);
        final List<Unit> units = new ArrayList<>();
        for (Cell cell : cells) {
            final Unit unit = getDetail(cell);
            units.add(unit);
        }
        return units;
    }

    public static Unit getDetail(final Cell cell) {
        final CellStatus cellStatus = new CellStatus(cell);
        final Vacuuming vacuuming = cell.getLastVacuuming();
        final LocalDate today = new LocalDate();
        if (vacuuming != null) {
            final LocalDate vacuumed = LocalDate.fromDateFields(vacuuming.getHappened());
            final int term = Days.daysBetween(vacuumed, today).getDays();
            cellStatus.setComment(vacuuming.comment);
            cellStatus.setTerm(term);
            cellStatus.setVacuumed(vacuumed.toDate());
        }
        final List<Take> takes = Take.findByCell(cell);
            final List<TakeStatus> takeStatuses = new ArrayList<>();
        for (Take take : takes) {
            TakeStatus takeStatus = new TakeStatus(take);
            final TakeScrubbing scrubbing = take.getLastScrubbing();
            takeStatuses.add(takeStatus);
            if (scrubbing != null) {
                final LocalDate scrubbed = LocalDate.fromDateFields(scrubbing.getHappened());
                final int term = Days.daysBetween(scrubbed, today).getDays();
                takeStatus.setComment(scrubbing.comment);
                takeStatus.setFirstDamage(scrubbing.firstDamage);
                takeStatus.setScrubbed(scrubbing.getHappened());
                takeStatus.setTerm(term);
            }
        }
        return new Unit(cellStatus, takeStatuses);
    }
}
