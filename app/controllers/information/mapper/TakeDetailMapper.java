package controllers.information.mapper;

import controllers.information.dto.TakeDetailDto;
import models.information.TakeScrubbing;
import models.information.TakeDetail;
import org.joda.time.Days;
import org.joda.time.LocalDate;

public class TakeDetailMapper {

    public static void toDetail(final TakeDetail takeDetail, final TakeDetailDto dto) {
        takeDetail.checked = true;
        takeDetail.scrubbed = dto.moment;
        takeDetail.comment = dto.comment;
        takeDetail.machined = dto.machined;
        takeDetail.firstDamage = dto.firstDamage;
        takeDetail.toChange = dto.toChange;
        takeDetail.changed = dto.changed;
    }

    public static void toDetail(final TakeDetail takeDetail, final TakeScrubbing scrubbing) {
        if (scrubbing == null) {
            makeDefault(takeDetail);
        } else {
            final LocalDate scrubbed = new LocalDate(scrubbing.getHappened());
            final int term = Days.daysBetween(scrubbed, new LocalDate()).getDays();
            takeDetail.scrubbed = scrubbed.toDate();
            takeDetail.comment = scrubbing.comment;
            takeDetail.machined = scrubbing.machined != null ? scrubbing.machined : takeDetail.take.getQuantity();
            takeDetail.firstDamage = scrubbing.firstDamage != null ? scrubbing.firstDamage : 0;
            takeDetail.toChange = scrubbing.toChange != null ? scrubbing.toChange : 0;
            takeDetail.changed = scrubbing.changed != null ? scrubbing.changed : 0;
            takeDetail.term = term;
        }
    }

    private static void makeDefault(final TakeDetail takeDetail) {
        takeDetail.scrubbed = null;
        takeDetail.comment = "-";
        takeDetail.machined = takeDetail.take.getQuantity();
        takeDetail.firstDamage = 0;
        takeDetail.toChange = 0;
        takeDetail.changed = 0;
        takeDetail.term = 0;
    }

}
