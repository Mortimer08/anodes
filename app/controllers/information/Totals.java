package controllers.information;

import controllers.Bases;
import controllers.information.repo.TotalRepo;
import controllers.util.DateUtils;
import play.modules.router.Get;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

public class Totals extends Bases {

    @Get("/total")
    public static void view() {
        final Date begin = DateUtils.yearFirstDate(new Date());
        final Date end = DateUtils.yearLastDate(new Date());
        final List<Tuple> results = TotalRepo.findTakeResults(begin, end);
        final List<Tuple> cellTerms = TotalRepo.findCellTerms();
        final List<Tuple> takeTerms = TotalRepo.findTakeTerms();
        final Integer cellMaxTerm = TotalRepo.findCellMaxTerm();
        final Integer takeMaxTerm = TotalRepo.findTakeMaxTerm();
        final Tuple total = TotalRepo.findTakeTotal(begin, end);
        render(results, cellTerms, cellMaxTerm, takeTerms, takeMaxTerm, total, begin, end);
    }
}
