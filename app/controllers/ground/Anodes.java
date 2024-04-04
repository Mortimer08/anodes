package controllers.ground;

import controllers.Bases;
import controllers.ground.dto.AnodeCreate;
import controllers.ground.dto.AnodeUpdate;
import controllers.ground.dto.CellCreate;
import controllers.ground.dto.TakeUpdate;
import controllers.ground.mapper.AnodeMapper;
import controllers.ground.mapper.CellMapper;
import controllers.ground.mapper.TakeMapper;
import models.ground.Anode;
import models.ground.Cell;
import models.ground.Take;
import play.modules.router.Get;
import play.modules.router.Post;

import java.util.List;

public class Anodes extends Bases {
    @Get("/anodes/list")
    public static void list() {
        List<Anode> anodes = Anode.findAll();
        render(anodes);
    }

    @Get("/anodes/blank")
    public static void blank() {
        render();
    }

    @Post("/anodes/create")
    public static void create(final AnodeCreate rq) {
        final Anode anode = new Anode();
        AnodeMapper.toEntity(anode, rq);
        System.out.println(anode.name);
        anode.save();
        list();
    }

    @Get("/anodes/view/{<\\d+>id}")
    public static void view(final Long id) {
        final Anode anode = Anode.findById(id);
        render(anode);
    }
    @Get("/anodes/show/{<\\d+>id}")
    public static void show(final Long id) {
        final Anode anode = Anode.findById(id);
        render(anode);
    }
    @Get("/anodes/delete/{<\\d+>id}")
    public static void delete(final Long id) {
        final Anode anode = Anode.findById(id);
        anode.delete();
        list();
    }
    @Post("/anodes/save/{<\\d+>id}")
    public static void save(final Long id, final AnodeUpdate rq) {
        final Anode anode = Anode.findById(id);
        AnodeMapper.toEntity(anode, rq);
        anode.save();
        list();
    }
}
