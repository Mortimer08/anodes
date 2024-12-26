package models.ground;

import common.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class Unit extends BaseModel {

    public Cell cell;
    public List<Take> takes;

    public Unit(Cell cell, List<Take> takes) {
        this.cell = cell;
        this.takes = takes;
    }

    public static List<Unit> findUnits() {
        List<Cell> rowA = Cell.findByRow(Row.find("name like 'A'").first());
        List<Unit> units = new ArrayList<>();
        for (Cell cell : rowA) {
            List<Take> takes = Take.findByCell(cell);
            units.add(new Unit(cell, takes));
        }
        return units;
    }
}
