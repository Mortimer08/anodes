package controllers.information.mapper;

import controllers.information.dto.CellDetailDto;
import controllers.information.dto.VacuumingUpdate;
import models.ground.Cell;
import models.information.CellDetail;
import models.information.Vacuuming;

public class VacuumingMapper {

    public static void toEntity(final CellDetailDto dto, Vacuuming vacuuming) {
        vacuuming.cell = Cell.findById(dto.id);
        vacuuming.happened = dto.happened;
    }

    public static void toEntity(final Vacuuming vacuuming, CellDetail cellDetail) {
        vacuuming.cell = cellDetail.cell;
        vacuuming.happened = cellDetail.vacuumed;
        vacuuming.comment = cellDetail.comment;
    }

    public static void toEntity(final Vacuuming vacuuming, final VacuumingUpdate vacuumingUpdate) {
        vacuuming.happened = vacuumingUpdate.happened;
        vacuuming.comment = vacuumingUpdate.comment;
    }

}
