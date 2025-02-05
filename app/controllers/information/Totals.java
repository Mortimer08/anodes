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
        final List<Tuple> cellTerms = TotalRepo.findCellTerms();
        final List<Tuple> takeTerms = TotalRepo.findTakeTerms();
        final Integer cellMaxTerm = TotalRepo.findCellMaxTerm();
        final Integer takeMaxTerm = TotalRepo.findTakeMaxTerm();
        final Tuple total = TotalRepo.findTakeTotal(begin, end);
        Tuple[] res = new Tuple[5];
        Tuple[] cellTerm = new Tuple[5];
        for (Team team : Team.values()) {
            cellTerm[team.ordinal()] = TotalRepo.findCellTerm(team);
            res[team.ordinal()] = TotalRepo.findTeamResult(team, begin, end);
        }
        render(res, cellTerm, cellTerms, cellMaxTerm, takeTerms, takeMaxTerm, total, begin, end);
    }


    @Post("/total/period")
    public static void period(final Date begin, final Date end) {
        final List<Tuple> cellTerms = TotalRepo.findCellTerms();
        final List<Tuple> takeTerms = TotalRepo.findTakeTerms();
        final Integer cellMaxTerm = TotalRepo.findCellMaxTerm();
        final Integer takeMaxTerm = TotalRepo.findTakeMaxTerm();
        final Tuple total = TotalRepo.findTakeTotal(begin, end);
        Tuple[] res = new Tuple[5];
        for (Team team : Team.values()) {
            res[team.ordinal()] = TotalRepo.findTeamResult(team, begin, end);
        }
        render(res, cellTerms, cellMaxTerm, takeTerms, takeMaxTerm, total, begin, end);
    }
}
