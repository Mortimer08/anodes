package controllers.ground;

import models.ground.Tier;
import play.modules.router.Get;
import play.modules.router.Post;
import play.mvc.Controller;
import controllers.ground.dto.TierUpdate;
import controllers.ground.mapper.TierMapper;

import java.util.List;

public class Tiers extends Controller {
    @Get("/tiers/list")
    public static void list() {
        List<Tier> tiers = Tier.findAll();
        render(tiers);
    }

    @Get("/tiers/blank")
    public static void blank() {
        render();
    }

    @Get("/tiers/create")
    public static void create(final TierUpdate rq) {
        final Tier tier = new Tier();
        TierMapper.toEntity(tier, rq);
        tier.save();
        list();
    }

    @Post("/tiers/save/{<\\d+>id}")
    public static void save(final Long id, final TierUpdate rq) {
        final Tier tier = Tier.findById(id);
        TierMapper.toEntity(tier, rq);
        tier.save();
        list();
    }

    @Get("/tiers/view/{<\\d+>id}")
    public static void view(final Long id) {
        final Tier tier = Tier.findById(id);
        render(tier);
    }

    @Get("/tiers/show/{<\\d+>id}")
    public static void show(final Long id) {
        final Tier tier = Tier.findById(id);
        render(tier);
    }

    @Get("/tiers/delete/{<\\d+>id}")
    public static void delete(final Long id) {
        final Tier tier = Tier.findById(id);
        tier.delete();
        list();
    }
}
