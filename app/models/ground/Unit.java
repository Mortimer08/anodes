package models.ground;

import models.Team;
import models.information.CellStatus;
import models.information.Vacuuming;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class Unit{

    public CellStatus cellStatus;
    public List<Take> takes;

    public Unit(CellStatus cellStatus, List<Take> takes) {
        this.cellStatus = cellStatus;
        this.takes = takes;
    }

    public static List<Unit> findByTeam(final Team team) {
        List<Cell> cells = Cell.findByTeam(team);
        List<Unit> units = new ArrayList<>();
        for (Cell cell : cells) {
            final CellStatus cellStatus = new CellStatus(cell);
            final Vacuuming vacuuming = Vacuuming.findLast(cell);
            if (vacuuming != null) {
                LocalDate vacuumed = LocalDate.fromDateFields(vacuuming.getHappened());
                final int days = Days.daysBetween(vacuumed, new LocalDate()).getDays();
                cellStatus.setComment(vacuuming.comment);
                cellStatus.setTerm(days);
                cellStatus.setVacuumed(vacuumed.toDate());
            }
            List<Take> takes = Take.findByCell(cell);
            units.add(new Unit(cellStatus, takes));
        }
        return units;
    }

    public static Unit getDetail(final Cell cell) {
        final CellStatus cellStatus = new CellStatus(cell);
        final Vacuuming vacuuming = Vacuuming.findLast(cell);
        if (vacuuming != null) {
            final LocalDate vacuumed = LocalDate.fromDateFields(vacuuming.getHappened());
            final int days = Days.daysBetween(vacuumed, new LocalDate()).getDays();
            cellStatus.setComment(vacuuming.comment);
            cellStatus.setTerm(days);
            cellStatus.setVacuumed(vacuumed.toDate());
        }
        final List<Take> takes = Take.findByCell(cell);
        Unit unit = new Unit(cellStatus, takes);
        return unit;
    }
}
