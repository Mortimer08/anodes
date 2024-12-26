package controllers.ground;

import controllers.Bases;
import controllers.ground.dto.CellCreate;
import controllers.ground.dto.CellUpdate;
import controllers.ground.mapper.CellMapper;
import controllers.information.dto.CleanDto;
import models.ground.Cell;
import models.ground.Take;
import models.ground.Unit;
import org.joda.time.LocalDate;
import play.modules.router.Get;
import play.modules.router.Post;

import java.util.Date;
import java.util.List;

public class Cells extends Bases {

    @Get("/cells/list")
    public static void list() {
        final String date = new LocalDate().toString();
        final List<Unit> units = Unit.findUnits();
        render(units, date);
    }

    @Post("/cells/clean")
    public static void clean(CleanDto rq) {
        Date date = rq.date;
        if (date == null) {
            date = new Date();
        }
        if (rq.cell != null) {
            for (Long id : rq.cell) {
                final Cell cell = Cell.findById(id);
                cell.clean(date);
            }
        }
        if (rq.take != null) {
            for (Long id : rq.take) {
                final Take take = Take.findById(id);
                take.clean(date);
            }
        }
        list();
    }

    @Get("/cells/blank")
    public static void blank() {
        render();
    }

    @Post("/cells/create")
    public static void create(final CellCreate rq) {
        final Cell cell = new Cell();
        CellMapper.toEntity(cell, rq);
        cell.save();
        list();
    }

    @Post("/cells/save/{<\\d+>id}")
    public static void save(final Long id, final CellUpdate rq) {
        final Cell cell = Cell.findById(id);
        CellMapper.toEntity(cell, rq);
        cell.save();
        list();
    }

    @Get("/cells/view/{<\\d+>id}")
    public static void view(final Long id) {
        final Cell cell = Cell.findById(id);
        render(cell);
    }

    @Get("/cells/show/{<\\d+>id}")
    public static void show(final Long id) {
        final Cell cell = Cell.findById(id);
        render(cell);
    }

    @Get("/cells/delete/{<\\d+>id}")
    public static void delete(final Long id) {
        final Cell cell = Cell.findById(id);
        cell.delete();
        list();
    }
}