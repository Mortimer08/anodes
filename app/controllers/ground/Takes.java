package controllers.ground;

import controllers.Bases;
import controllers.ground.dto.TakeCreate;
import controllers.ground.dto.TakeUpdate;
import controllers.ground.mapper.TakeMapper;
import models.ground.Take;
import play.modules.router.Get;
import play.modules.router.Post;

import java.util.Date;
import java.util.List;

public class Takes extends Bases {

    @Get("/takes/list")
    public static void list() {
        List<Take> takes = Take.findAll();
        render(takes);
    }

    @Post("/takes/clean")
    public static void clean(Long id) {
        Date date = new Date();
        System.out.println("clean take");
        Take take = Take.findById(id);
        take.clean(date);
        render();
    }

    @Get("/takes/blank")
    public static void blank() {
        render();
    }

    @Post("/takes/create")
    public static void create(final TakeCreate rq) {
        final Take take = new Take();
        TakeMapper.toEntity(take, rq);
        take.save();
        list();
    }

    @Get("/takes/view/{<\\d+>id}")
    public static void view(final Long id) {
        final Take take = Take.findById(id);
        render(take);
    }

    @Get("/takes/show/{<\\d+>id}")
    public static void show(final Long id) {
        final Take take = Take.findById(id);
        render(take);
    }

    @Get("/takes/delete/{<\\d+>id}")
    public static void delete(final Long id) {
        final Take take = Take.findById(id);
        take.delete();
        list();
    }

    @Post("/takes/save/{<\\d+>id}")
    public static void save(final Long id, final TakeUpdate rq) {
        final Take take = Take.findById(id);
        TakeMapper.toEntity(take, rq);
        take.save();
        list();
    }
}
