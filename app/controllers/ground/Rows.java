package controllers.ground;

import controllers.Bases;
import controllers.ground.dto.RowCreate;
import controllers.ground.dto.RowUpdate;
import controllers.ground.mapper.RowMapper;
import models.ground.Row;
import play.modules.router.Get;
import play.modules.router.Post;

import java.util.List;

public class Rows extends Bases {
    @Get("/rows/list")
    public static void list() {
        List<Row> rows = Row.findAll();
        render(rows);
    }

    @Get("/rows/view/{<\\d+>id}")
    public static void view(final Long id) {
        final Row row = Row.findById(id);
        render(row);
    }

    @Get("/rows/delete/{<\\d+>id}")
    public static void delete(final Long id) {
        final Row row = Row.findById(id);
        row.delete();
        list();
    }

    @Get("/rows/show/{<\\d+>id}")
    public static void show(final Long id) {
        final Row row = Row.findById(id);
        render(row);
    }

    @Post("/rows/create")
    public static void create(final RowCreate rq) {
        final Row row = new Row();
        RowMapper.toEntity(row, rq);
        row.save();
        list();
    }

    @Post("/rows/save/{<\\d+>id}")
    public static void save(final Long id, final RowUpdate rq) {
        final Row row = Row.findById(id);
        RowMapper.toEntity(row, rq);
        row.save();
        list();
    }

    @Get("/rows/blank")
    public static void blank() {
        render();
    }
}
