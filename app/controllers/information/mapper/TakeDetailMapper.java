package controllers.information.mapper;

import controllers.information.dto.TakeDetailDto;
import models.ground.TakeNumber;
import models.information.TakeScrubbing;
import models.information.TakeDetail;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.Date;

public class TakeDetailMapper {

    public static void toDetail(final TakeDetail takeDetail, final TakeDetailDto dto) {
        if (dto.moment == null) dto.moment = new Date();
        takeDetail.checked = true;
        takeDetail.scrubbed = dto.moment;
        takeDetail.comment = dto.comment;
        if (dto.machined != null) {
            takeDetail.machined = TakeNumber.getQuantity(dto.takeNumber);
            takeDetail.handled = 0;
        } else {
            takeDetail.machined = 0;
            takeDetail.handled = TakeNumber.getQuantity(dto.takeNumber);
        }
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
            takeDetail.handled = scrubbing.handled != null ? scrubbing.handled : 0;
            takeDetail.firstDamage = scrubbing.firstDamage != null ? scrubbing.firstDamage : 0;
            takeDetail.toChange = scrubbing.toChange != null ? scrubbing.toChange : 0;
            takeDetail.changed = scrubbing.changed != null ? scrubbing.changed : 0;
            takeDetail.term = scrubbed == null ? null : term;
        }
    }

    private static void makeDefault(final TakeDetail takeDetail) {
        takeDetail.scrubbed = null;
        takeDetail.comment = null;
        takeDetail.machined = takeDetail.take.getQuantity();
        takeDetail.handled = 0;
        takeDetail.firstDamage = 0;
        takeDetail.toChange = 0;
        takeDetail.changed = 0;
        takeDetail.term = null;
    }

}
