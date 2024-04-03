package controllers.ground.mapper;

import controllers.ground.dto.TakeCreate;
import controllers.ground.dto.TakeUpdate;
import models.ground.Cell;
import models.ground.Take;

public class TakeMapper {
    public static void toEntity(Take take, TakeCreate rq) {
        take.number = rq.number;
        take.cell = Cell.findById(rq.cell);
        take.name = String.format("%s(%s)", take.cell, take.number.number);
    }
    public static void toEntity(Take take, TakeUpdate rq) {
        take.number = rq.number;
        take.cell = Cell.findById(rq.cell);
        take.name = String.format("%s(%s)", take.cell, take.number.number);
    }
}
