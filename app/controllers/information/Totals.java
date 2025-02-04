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
        final List<Tuple> results = TotalRepo.findResults();
        final Tuple total = TotalRepo.findTotal();
        final Date begin = DateUtils.yearFirstDate(new Date());
        final Date end = DateUtils.yearLastDate(new Date());
        render(results, total, begin, end);
    }
}
