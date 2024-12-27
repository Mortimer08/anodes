package controllers.action;

import controllers.Bases;
import controllers.information.dto.CleanDto;
import models.Team;
import models.ground.Cell;
import models.ground.Take;
import models.ground.Unit;
import org.joda.time.LocalDate;
import play.Logger;
import play.modules.router.Get;
import play.modules.router.Post;

import java.util.Date;
import java.util.List;

public class Cleanings extends Bases {

    @Get("/cleaning/team/{<\\d+>number}")
    public static void list(int number) {
        final Team team = Team.getTeam(number);
        notFoundIfNull(team);
        final String date = new LocalDate().toString();
        final List<Unit> units = Unit.findByTeam(team);
        render(units, date, number);
    }

    @Post("/cleaning/clean/{<\\d+>number}")
    public static void clean(int number, CleanDto rq) {
        Date date = rq.date;
        if (date == null) {
            date = new Date();
        }
        if (rq.cell != null) {
            for (Long id : rq.cell) {
                final Cell cell = Cell.findById(id);
                cell.clean(date);
            }
        }
        if (rq.take != null) {
            for (Long id : rq.take) {
                final Take take = Take.findById(id);
                take.clean(date);
            }
        }
        list(number);
    }

    @Post("/cleanings/detail/{<\\d+>id}")
    public static void detail(final long id) {
        final Cell cell = Cell.findById(id);
        notFoundIfNull(cell);
        Unit unit = Unit.getDetail(cell);
        render(unit);
    }
}
