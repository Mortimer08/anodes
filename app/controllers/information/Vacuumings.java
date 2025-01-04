package controllers.information;

import controllers.Bases;
import controllers.util.DateUtils;
import models.Team;
import models.information.Vacuuming;
import play.modules.router.Get;
import play.modules.router.Post;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

public class Vacuumings extends Bases {

    @Get("/vacuuming")
    public static void list() {
        final List<Integer> teamIndex = Team.getIndexes();
        final Date today = new Date();
        final Date begin = DateUtils.monthFirstDate(today);
        final Date end = DateUtils.monthLastDate(today);
        final Date start = DateUtils.plusDays(begin, -1);
        final Date finish = DateUtils.plusDays(end, 1);
        final List<Tuple> vacuumings = Vacuuming.findByFilter(teamIndex, start, finish);
        render(vacuumings, begin, end);
    }

    @Post("/vacuuming/filter")
    public static void filter(final List<Integer> teamIndex, Date begin, Date end) {
        begin = DateUtils.plusDays(begin, -1);
        end = DateUtils.plusDays(end, 1);
        final List<Tuple> vacuumings = Vacuuming.findByFilter(teamIndex, begin, end);
        render(vacuumings);
    }

}
