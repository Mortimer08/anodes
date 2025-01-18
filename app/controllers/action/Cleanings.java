package controllers.action;

import controllers.Bases;
import controllers.information.service.UnitService;
import controllers.information.dto.CellDetailDto;
import controllers.information.dto.TakeDetailDto;
import controllers.information.mapper.CellDetailMapper;
import controllers.information.mapper.TakeDetailMapper;
import controllers.information.mapper.TakeScrubbingMapper;
import controllers.information.mapper.VacuumingMapper;
import models.Team;
import models.ground.Cell;
import models.ground.Take;
import models.information.*;
import org.joda.time.LocalDate;
import play.modules.router.Get;
import play.modules.router.Post;

import java.util.*;

public class Cleanings extends Bases {

    private static Map<Long, Unit> unitMap = new TreeMap<>();

    @Get("/cleaning/team/{<\\d+>number}")
    public static void list(int number) {
        final Team team = Team.getTeam(number);
        notFoundIfNull(team);
        final Date date = LocalDate.now().toDate();
        final List<Unit> units = UnitService.findByTeam(team);
        unitMap = UnitService.mapFrom(units);
        final Integer maxCellTerm = getCellMaxTerm();
        final Integer maxTakeTerm = getTakeMaxTerm();
        final Integer firstDamageSum = getFirstDamageSum();
        final Integer toChangeSum = getToChangeSum();
        final Integer damaged = firstDamageSum + toChangeSum;
        render(units, date, number, maxCellTerm, maxTakeTerm, firstDamageSum, toChangeSum, damaged);
    }

    @Post("/cleaning/clean/{<\\d+>number}")
    public static void clean(int number) {
        cleanChecked();
        list(number);
    }

    @Post("/cleanings/detail/{<\\d+>id}")
    public static void detail(final long id) {
        final Unit unit = getUnit(id);
        render(unit);
    }

    @Post("/cleanings/cell/detail/{<\\d+>id}")
    public static void cellDetail(final long id, final CellDetailDto rq) {
        final Unit unit = getUnit(id);
        correctUnit(unit, rq);
        render("action/Cleanings/detail.html", unit);
    }

    @Post("/cleanings/take/detail/{<\\d+>id}")
    public static void takeDetail(final long id, final TakeDetailDto rq) {
        final Unit unit = getUnit(id);
        correctUnit(unit, rq);
        render("action/Cleanings/detail.html", unit);
    }

    @Post("/cleanings/cell/detail/change/{<\\d+>id}")
    public static void cellChange(final long id, final CellDetailDto rq) {
        final Unit unit = getUnit(id);
        correctUnit(unit, rq);
        render(unit, id);
    }

    @Post("/cleanings/take/detail/change/{<\\d+>id}")
    public static void takeChange(final long id, final TakeDetailDto rq) {
        final Unit unit = getUnit(id);
        correctUnit(unit, rq);
        final TakeDetail tDetail = unit.takeDetailById(rq.id);
        render(unit, id, tDetail);
    }

    private static void correctUnit(final Unit unit, final CellDetailDto rq) {
        CellDetailMapper.toDetail(unit.cellDetail, rq);
    }

    private static void correctUnit(final Unit unit, final TakeDetailDto rq) {
        final TakeDetail takeDetail = unit.takeDetailById(rq.id);
        if (takeDetail != null) {
            TakeDetailMapper.toDetail(takeDetail, rq);
        } else {
            for (TakeDetail tDetail : unit.takeDetails) {
                tDetail.checked = false;
                final Take take = Take.findById(tDetail.take.id);
                final TakeScrubbing takeScrubbing = TakeScrubbing.findLast(take);
                TakeDetailMapper.toDetail(tDetail, takeScrubbing);
            }
        }
    }

    private static Unit getUnit(final long id) {
        final Cell cell = Cell.findById(id);
        final Team team = cell.team;
        if (unitMap.isEmpty() || !isBelongTo(team)) {
            notFoundIfNull(team);
            final List<Unit> units = UnitService.findByTeam(team);
            unitMap = UnitService.mapFrom(units);
        }
        return unitMap.get(id);
    }

    private static void cleanChecked() {
        for (Unit unit : unitMap.values()) {
            CellDetail cellDetail = unit.cellDetail;
            Vacuuming vacuuming = new Vacuuming();
            if (cellDetail.isVacuumed()) {
                VacuumingMapper.toEntity(vacuuming, cellDetail);
                vacuuming.save();
            }
            if (unit.takeDetails != null) {
                for (TakeDetail takeDetail : unit.takeDetails) {
                    if (takeDetail.isScrubbed()) {
                        final TakeScrubbing takeScrubbing = new TakeScrubbing();
                        TakeScrubbingMapper.toEntity(takeScrubbing, takeDetail);
                        takeScrubbing.save();
                    }
                }
            }
        }
    }

    private static Integer getCellMaxTerm() {
        Integer maxTerm = 0;
        for (Unit unit : unitMap.values()) {
            if (unit.getCellTerm() != null && maxTerm < unit.getCellTerm()) {
                maxTerm = unit.getCellTerm();
            }
        }
        return maxTerm;
    }

    private static Integer getTakeMaxTerm() {
        Integer maxTerm = 0;
        for (Unit unit : unitMap.values()) {
            if (unit.getTakeMaxTerm() > maxTerm) maxTerm = unit.getTakeMaxTerm();
        }
        return maxTerm;
    }

    private static Integer getFirstDamageSum() {
        Integer sum = 0;
        for (Unit unit : unitMap.values()) {
            sum += unit.getFirstDamageSum();
        }
        return sum;
    }

    private static Integer getToChangeSum() {
        Integer sum = 0;
        for (Unit unit : unitMap.values()) {
            sum += unit.getToChangeSum();
        }
        return sum;
    }

    private static boolean isBelongTo(final Team team) {
        final Long cellId = (Long) unitMap.keySet().toArray()[0];
        final Cell cell = Cell.findById(cellId);
        return cell.team.equals(team);
    }

}
