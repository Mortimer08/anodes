package controllers.information;

import controllers.Bases;
import controllers.information.dto.ScrubbingFilter;
import controllers.information.dto.ScrubbingUpdate;
import controllers.information.mapper.TakeScrubbingMapper;
import controllers.information.report.ScrubbingReport;
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

    @Post("/scrubbing/report")
    public static void report() {
        final ScrubbingFilter f = getNewFilter(ScrubbingFilter.class);
        final List<Tuple> items = TakeScrubbing.findByFilter(f);
        final String reportName = ScrubbingReport.create(items);
        response.setHeader("HX-Redirect", "/download/" + reportName);
        ok();
    }

    @Get("/scrubbing/view/{<\\d+>id}")
    public static void view(final Long id) {
        final TakeScrubbing takeScrubbing = TakeScrubbing.findById(id);
        notFoundIfNull(takeScrubbing);
        render(takeScrubbing);
    }

    @Get("/scrubbing/show/{<\\d+>id}")
    public static void show(final Long id) {
        final TakeScrubbing takeScrubbing = TakeScrubbing.findById(id);
        notFoundIfNull(takeScrubbing);
        render(takeScrubbing);
    }

    @Post("/scrubbing/save/{<\\d+>id}")
    public static void save(final Long id, final ScrubbingUpdate rq) {
        final TakeScrubbing takeScrubbing = TakeScrubbing.findById(id);
        notFoundIfNull(takeScrubbing);
        TakeScrubbingMapper.toEntity(takeScrubbing, rq);
        takeScrubbing.save();
        list();
    }
}
