package controllers.information.mapper;

import controllers.information.dto.ScrubbingUpdate;
import controllers.information.dto.TakeDetailDto;
import models.ground.Take;
import models.information.TakeDetail;
import models.information.TakeScrubbing;

public class TakeScrubbingMapper {

    public static void toEntity(final TakeScrubbing takeScrubbing, TakeDetailDto dto) {
        takeScrubbing.take = Take.findById(dto.id);
        takeScrubbing.machined = dto.machined;
        takeScrubbing.firstDamage = dto.firstDamage;
        takeScrubbing.toChange = dto.toChange;
        takeScrubbing.changed = dto.changed;
    }

    public static void toEntity(final TakeScrubbing takeScrubbing, final TakeDetail takeDetail) {
        takeScrubbing.take = takeDetail.take;
        takeScrubbing.happened = takeDetail.scrubbed;
        takeScrubbing.machined = takeDetail.machined == null ? 0 : takeDetail.machined;
        takeScrubbing.changed = takeDetail.changed;
        takeScrubbing.toChange = takeDetail.toChange;
        takeScrubbing.comment = takeDetail.comment;
        takeScrubbing.firstDamage = takeDetail.firstDamage;
    }

    public static void toEntity(final TakeScrubbing takeScrubbing, final ScrubbingUpdate scrubbingUpdate) {
        takeScrubbing.happened = scrubbingUpdate.happened;
        takeScrubbing.machined = scrubbingUpdate.machined;
        takeScrubbing.handled = scrubbingUpdate.handled;
        takeScrubbing.changed = scrubbingUpdate.changed;
        takeScrubbing.firstDamage = scrubbingUpdate.firstDamage;
        takeScrubbing.toChange = scrubbingUpdate.toChange;
        takeScrubbing.comment = scrubbingUpdate.comment;
    }
}
