package controllers.information;

import controllers.Bases;
import controllers.information.dto.VacuumingFilter;
import controllers.information.view.VacuumingView;
import models.information.Vacuuming;
import play.modules.router.Get;
import play.modules.router.Post;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

public class Vacuumings extends Bases {

    @Get("/vacuuming")
    public static void list() {
        final VacuumingFilter f = getNewFilter(VacuumingFilter.class);
        final List<Tuple> vacuumings = Vacuuming.findByFilter(f);
        render(vacuumings, f);
    }

    @Post("/vacuuming/filter/team")
    public static void filterTeam(final List<Integer> teamIndex) {
        final VacuumingFilter f = getNewFilter(VacuumingFilter.class);
        f.updateIndex(teamIndex);
        updateFilter(f);
        final List<Tuple> vacuumings = Vacuuming.findByFilter(f);
        render("information/Vacuumings/filter.html", vacuumings, f);
    }

    @Post("/vacuuming/filter/date")
    public static void filterDate(final Date begin, final Date end) {
        final VacuumingFilter f = getNewFilter(VacuumingFilter.class);
        f.updateDates(begin, end);
        updateFilter(f);
        final List<Tuple> vacuumings = Vacuuming.findByFilter(f);
        render("information/Vacuumings/filter.html", vacuumings, f);
    }

    @Post("/vacuuming/sort")
    public static void sort(VacuumingView sort) {
        final VacuumingFilter f = getNewFilter(VacuumingFilter.class);
        f.setSort(sort);
        updateFilter(f);
        final List<Tuple> vacuumings = Vacuuming.findByFilter(f);
        render("/information/Vacuumings/filter.html", vacuumings, f);
    }

}
