package controllers.ground;

import controllers.Bases;
import controllers.ground.dto.CellCreate;
import controllers.ground.dto.CellUpdate;
import controllers.ground.mapper.CellMapper;
import controllers.information.dto.CleanDto;
import models.ground.Cell;
import models.ground.Take;
import play.modules.router.Get;
import play.modules.router.Post;

import java.util.Date;
import java.util.List;

public class Cells extends Bases {

    @Get("/cells/list")
    public static void list() {
        final List<Cell> cells = Cell.findAll();
        render(cells);
    }

    @Post("/cells/clean")
    public static void clean(CleanDto rq) {
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