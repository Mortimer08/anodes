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
import play.Logger;
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
        render(units, date, number);
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
        Logger.info("take changed: " + rq.id);

        final Take take = Take.findById(rq.id);
        notFoundIfNull(take);
        final Unit unit = getUnit(id);
        correctUnit(unit, rq);
        final Long takeId = take.id;
        render(id, takeId);
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
        if (unitMap.isEmpty()) {
            final Cell cell = Cell.findById(id);
            final Team team = cell.team;
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

}
