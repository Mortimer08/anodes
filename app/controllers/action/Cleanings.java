package controllers.action;

import controllers.Bases;
import models.Team;
import models.ground.Unit;
import org.joda.time.LocalDate;
import play.Logger;
import play.modules.router.Get;

import java.util.List;

public class Cleanings extends Bases {

    @Get("/cleaning/team/{<\\d+>number}")
    public static void list(int number) {
        final Team team = Team.getTeam(number);
        notFoundIfNull(team);
        final String date = new LocalDate().toString();
        final List<Unit> units = Unit.findByTeam(team);
        render(units, date);
    }
}
