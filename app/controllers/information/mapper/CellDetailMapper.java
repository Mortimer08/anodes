package controllers.information.mapper;

import controllers.information.dto.CellDetailDto;
import models.ground.Cell;
import models.information.CellDetail;
import models.information.Vacuuming;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.Date;

public class CellDetailMapper {

    public static void toDetail(final CellDetail cellDetail, final Vacuuming vacuuming) {
        if (vacuuming == null) {
            makeDefault(cellDetail);
        } else {
            final LocalDate vacuumed = new LocalDate(vacuuming.getHappened());
            final Integer term = Days.daysBetween(vacuumed, new LocalDate()).getDays();
            cellDetail.comment = vacuuming.comment;
            cellDetail.term = vacuumed == null ? null : term;
            cellDetail.vacuumed = vacuuming.happened;
        }
    }

    public static void toDetail(final CellDetail cellDetail, final CellDetailDto rq) {
        cellDetail.checked = rq.id != null;
        if (rq.id == null) {
            final Cell cell = Cell.findById(cellDetail.cell.id);
            final Vacuuming vacuuming = Vacuuming.findLast(cell);
            toDetail(cellDetail, vacuuming);
        } else {
            if (rq.happened == null) rq.happened = new Date();
            final LocalDate vacuumed = new LocalDate(rq.happened);
            final int term = Days.daysBetween(vacuumed, new LocalDate()).getDays();
            cellDetail.comment = rq.comment;
            cellDetail.term = term;
            cellDetail.vacuumed = rq.happened;
        }
    }

    private static void makeDefault(final CellDetail cellDetail) {
        cellDetail.comment = "";
        cellDetail.term = null;
        cellDetail.vacuumed = null;
    }
}
