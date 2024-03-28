package controllers.ground.mapper;

import controllers.ground.dto.RowCreate;
import controllers.ground.dto.RowUpdate;
import models.ground.Row;
import models.ground.Tier;

public class RowMapper {
    public static void toEntity(Row row, RowCreate rq) {
        row.name = rq.name;
        row.tier = Tier.findById(rq.tier);
    }
    public static void toEntity(Row row, RowUpdate rq) {
        row.name = rq.name;
        row.tier = Row.findById(rq.tier);
    }
}
