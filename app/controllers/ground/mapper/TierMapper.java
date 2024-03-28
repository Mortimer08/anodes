package controllers.ground.mapper;

import models.ground.Tier;
import controllers.ground.dto.TierCreate;
import controllers.ground.dto.TierUpdate;

public class TierMapper {
    public static void toEntity(Tier tier, TierCreate rq) {
        tier.name = rq.name;
    }

    public static void toEntity(Tier tier, TierUpdate rq) {
        tier.name = rq.name;
    }
}
