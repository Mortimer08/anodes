package controllers.information;

import controllers.Bases;
import controllers.information.repo.ResultRepo;
import controllers.util.DateUtils;
import play.modules.router.Get;
import play.modules.router.Post;

import java.util.Arrays;
import java.util.Date;

public class Results extends Bases {

    @Get("/result")
    public static void view() {
        final Date begin = DateUtils.monthFirstDate(new Date());
        final Date end = DateUtils.monthLastDate(new Date());
        display("information/Results/view.html", begin, end);
    }

    @Post("/result/period")
    public static void period(final Date begin, final Date end) {
        display("information/Results/period.html", begin, end);
    }

    private static void display(final String url, final Date begin, final Date end) {
        final Integer[] vacuumings = ResultRepo.countVacuuming(begin, end);
        final Integer[] machined = ResultRepo.countMachined(begin, end);
        final Integer[] handled = ResultRepo.countHandled(begin, end);
        final Integer[] changed = ResultRepo.countChanged(begin, end);
        final Integer vacuumingsTotal = Arrays.stream(vacuumings).mapToInt(Integer::intValue).sum();
        final Integer machinedTotal = Arrays.stream(machined).mapToInt(Integer::intValue).sum();
        final Integer handledTotal = Arrays.stream(handled).mapToInt(Integer::intValue).sum();
        final Integer changedTotal = Arrays.stream(changed).mapToInt(Integer::intValue).sum();
        render(url, begin, end,
                vacuumings, vacuumingsTotal,
                machined, machinedTotal,
                handled, handledTotal,
                changed, changedTotal);
    }
}
