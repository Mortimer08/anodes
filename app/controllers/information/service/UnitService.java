package controllers.information.service;

import controllers.information.mapper.CellDetailMapper;
import controllers.information.mapper.TakeDetailMapper;
import models.Team;
import models.ground.Cell;
import models.ground.Take;
import models.information.*;

import java.util.*;

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

    public static Map<Long, Unit> mapFrom(List<Unit> units) {
        Map<Long, Unit> unitMap = new TreeMap<>();
        for (Unit unit : units) {
            unitMap.put(unit.cellDetail.cell.id, unit);
        }
        return unitMap;
    }

    public static Unit getDetail(final Cell cell) {
        final CellDetail cellDetail = new CellDetail(cell);
        final Vacuuming vacuuming = cell.getLastVacuuming();
        CellDetailMapper.toDetail(cellDetail, vacuuming);
        final List<Take> takes = Take.findByCell(cell);
        final List<TakeDetail> takeDetails = new ArrayList<>();
        for (Take take : takes) {
            final TakeDetail takeDetail = new TakeDetail(take);
            final TakeScrubbing scrubbing = take.getLastScrubbing();
            takeDetails.add(takeDetail);
            TakeDetailMapper.toDetail(takeDetail, scrubbing);
        }
        return new Unit(cellDetail, takeDetails);
    }

}
