package controllers.ground.mapper;

import controllers.ground.dto.CellCreate;
import controllers.ground.dto.CellUpdate;
import models.ground.Cell;
import models.ground.Row;

public class CellMapper {
    public static void toEntity(Cell cell, CellCreate rq) {
        cell.number = rq.number;
        cell.row = Row.findById(rq.row);
        cell.name = cell.row.name.concat(String.valueOf(cell.number));
    }

    public static void toEntity(Cell cell, CellUpdate rq) {
        cell.number = rq.number;
        cell.row = Row.findById(rq.row);
        cell.name = cell.row.name.concat(String.valueOf(cell.number));
    }
}
