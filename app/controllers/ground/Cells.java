package controllers.ground;

import controllers.Bases;
import controllers.ground.dto.CellCreate;
import controllers.ground.dto.CellUpdate;
import controllers.ground.mapper.CellMapper;
import models.ground.Cell;
import models.ground.Row;
import play.modules.router.Get;
import play.modules.router.Post;

import java.util.Date;
import java.util.List;

public class Cells extends Bases {
    @Get("/cells/list")
    public static void list() {
        List<Cell> rowA = Cell.findByRow(Row.find("name like 'A'").first());
        List<Cell> rowB = Cell.findByRow(Row.find("name like 'B'").first());
        List<Cell> rowC = Cell.findByRow(Row.find("name like 'C'").first());
        List<Cell> rowD = Cell.findByRow(Row.find("name like 'D'").first());
        render(rowA, rowB, rowC, rowD);
    }

    @Post("/cells/clean/{<\\d+>id}")
    public static void clean(Long id){
        Date date = new Date();
        Cell cell = Cell.findById(id);
        cell.clean(date);
        render();
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