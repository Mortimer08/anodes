package controllers.ground.mapper;

import controllers.ground.dto.AnodeCreate;
import controllers.ground.dto.AnodeUpdate;
import models.ground.Anode;
import models.ground.Cell;
import models.ground.Take;
import models.ground.TakeNumber;

public class AnodeMapper {
    public static void toEntity(Anode anode, AnodeCreate rq) {
        anode.number = rq.number;
        Take take = Take.findByCellAndNumber(Cell.findById(rq.cell), TakeNumber.takeNumberByAnode(rq.number));
        anode.take = take;
        anode.name = String.format("%s(%s)_%s", take.cell, take.number.number, anode.number);
    }

    public static void toEntity(Anode anode, AnodeUpdate rq) {
        anode.number = rq.number;
        Take take = Take.findByCellAndNumber(Cell.findById(rq.cell), TakeNumber.takeNumberByAnode(rq.number));
        anode.take = take;
        anode.name = String.format("%s(%s)_%s", take.cell, take.number.number, anode.number);
    }
}
