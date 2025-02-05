package controllers.information;

import controllers.Bases;
import controllers.information.repo.TotalRepo;
import controllers.util.DateUtils;
import models.Team;
import play.modules.router.Get;
import play.modules.router.Post;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

public class Totals extends Bases {

    @Get("/total")
    public static void view() {
        final Date begin = DateUtils.yearFirstDate(new Date());
        final Date end = DateUtils.yearLastDate(new Date());
        final Integer cellMaxTerm = TotalRepo.findCellMaxTerm();
        final Integer takeMaxTerm = TotalRepo.findTakeMaxTerm();
        final Tuple total = TotalRepo.findTakeTotal(begin, end);
        Tuple[] res = new Tuple[Team.values().length];
        Integer[] cellTerm = new Integer[Team.values().length];
        Integer[] takeTerm = new Integer[Team.values().length];
        for (Team team : Team.values()) {
            cellTerm[team.ordinal()] = TotalRepo.findCellTerm(team);
            takeTerm[team.ordinal()] = TotalRepo.findTakeTerm(team);
            res[team.ordinal()] = TotalRepo.findTeamResult(team, begin, end);
        }
        render(res, cellTerm, cellTerm, cellMaxTerm, takeTerm, takeMaxTerm, total, begin, end);
    }


    @Post("/total/period")
    public static void period(final Date begin, final Date end) {
        final Integer cellMaxTerm = TotalRepo.findCellMaxTerm();
        final Integer takeMaxTerm = TotalRepo.findTakeMaxTerm();
        final Tuple total = TotalRepo.findTakeTotal(begin, end);
        Tuple[] res = new Tuple[Team.values().length];
        Integer[] cellTerm = new Integer[Team.values().length];
        Integer[] takeTerm = new Integer[Team.values().length];
        for (Team team : Team.values()) {
            cellTerm[team.ordinal()] = TotalRepo.findCellTerm(team);
            takeTerm[team.ordinal()] = TotalRepo.findTakeTerm(team);
            res[team.ordinal()] = TotalRepo.findTeamResult(team, begin, end);
        }
        render(res, cellTerm, cellMaxTerm, takeTerm, takeMaxTerm, total, begin, end);
    }
}
