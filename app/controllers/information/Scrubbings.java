package controllers.information;

import controllers.Bases;
import controllers.information.dto.ScrubbingFilter;
import controllers.information.view.ScrubbingView;
import models.information.TakeScrubbing;
import play.modules.router.Get;
import play.modules.router.Post;

import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

public class Scrubbings extends Bases {

    @Get("/scrubbing")
    public static void list() {
        final ScrubbingFilter f = getNewFilter(ScrubbingFilter.class);
        final List<Tuple> scrubbings = TakeScrubbing.findByFilter(f);
        render(scrubbings, f);
    }

    @Post("/scrubbing/filter/team")
    public static void filterTeam(final List<Integer> teamIndex) {
        final ScrubbingFilter f = getNewFilter(ScrubbingFilter.class);
        f.updateIndex(teamIndex);
        updateFilter(f);
        final List<Tuple> scrubbings = TakeScrubbing.findByFilter(f);
        render("information/Scrubbings/filter.html", scrubbings, f);
    }

    @Post("/scrubbing/filter/date")
    public static void filterDate(final Date begin, final Date end) {
        final ScrubbingFilter f = getNewFilter(ScrubbingFilter.class);
        f.updateDates(begin, end);
        updateFilter(f);
        final List<Tuple> scrubbings = TakeScrubbing.findByFilter(f);
        render("information/Scrubbings/filter.html", scrubbings, f);
    }

    @Post("/scrubbing/sort")
    public static void sort(final ScrubbingView sort) {
        final ScrubbingFilter f = getNewFilter(ScrubbingFilter.class);
        f.setSort(sort);
        updateFilter(f);
        final List<Tuple> scrubbings = TakeScrubbing.findByFilter(f);
        render("information/Scrubbings/filter.html", scrubbings, f);
    }
}
