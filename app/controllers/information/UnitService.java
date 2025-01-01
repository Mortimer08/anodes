package controllers.information;

import controllers.information.dto.CellDetailDto;
import controllers.information.dto.TakeDetailDto;
import controllers.information.mapper.CellDetailMapper;
import controllers.information.mapper.TakeDetailMapper;
import models.Team;
import models.ground.Cell;
import models.ground.Take;
import models.information.*;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class UnitService {

    public static List<Unit> findByTeam(final Team team) {
        final List<Cell> cells = Cell.findByTeam(team);
        final List<Unit> units = new ArrayList<>();
        for (Cell cell : cells) {
            final Unit unit = getDetail(cell);
            units.add(unit);
        }
        return units;
    }

    public static Unit getDetail(final Cell cell,
                                 final CellDetailDto cellDetailDto,
                                 final TakeDetailDto takeDetailDto) {
        final CellDetail cellStatus = new CellDetail(cell);
        if (cellDetailDto.id != null) {
            cellStatus.setVacuumed(cellDetailDto.happened);
            cellStatus.setComment(cellDetailDto.comment);
        } else {
            final Vacuuming vacuuming = cell.getLastVacuuming();
            if (vacuuming != null) {
                cellStatus.setVacuumed(vacuuming.getHappened());
                cellStatus.setComment(vacuuming.comment);
            }
        }
        final LocalDate today = new LocalDate();
        final LocalDate vacuumed = new LocalDate(cellDetailDto.happened);
        int term = Days.daysBetween(vacuumed, today).getDays();
        cellStatus.setTerm(term);
        final List<Take> takes = Take.findByCell(cell);
        final List<TakeDetail> takeStatuses = new ArrayList<>();
        for (Take take : takes) {
            TakeDetail takeStatus = new TakeDetail(take);
            if (take.id != null && take.id.equals(takeDetailDto.id)) {
                TakeDetailMapper.toEntity(takeStatus, takeDetailDto);
            } else {
                final TakeScrubbing scrubbing = take.getLastScrubbing();
                if (scrubbing != null) {
                    TakeDetailMapper.toEntity(takeStatus, scrubbing);
                }
            }
            final LocalDate scrubbed = new LocalDate(takeStatus.scrubbed);
            term = Days.daysBetween(scrubbed, today).getDays();
            takeStatus.setTerm(term);
            takeStatuses.add(takeStatus);
        }
        return new Unit(cellStatus, takeStatuses);
    }

    public static Unit getDetail(final Cell cell) {
        final CellDetail cellDetail = new CellDetail(cell);
        final Vacuuming vacuuming = cell.getLastVacuuming();
        if (vacuuming != null) {
            CellDetailMapper.toEntity(cellDetail, vacuuming);
        }
        final List<Take> takes = Take.findByCell(cell);
        final List<TakeDetail> takeStatuses = new ArrayList<>();
        for (Take take : takes) {
            TakeDetail takeDetail = new TakeDetail(take);
            final TakeScrubbing scrubbing = take.getLastScrubbing();
            takeStatuses.add(takeDetail);
            if (scrubbing != null) {
                TakeDetailMapper.toEntity(takeDetail, scrubbing);
            }
        }
        return new Unit(cellDetail, takeStatuses);
    }
}
