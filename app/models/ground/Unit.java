package models.ground;

import common.model.BaseModel;
import models.Team;

import java.util.ArrayList;
import java.util.List;

public class Unit extends BaseModel {

    public Cell cell;
    public List<Take> takes;

    public Unit(Cell cell, List<Take> takes) {
        this.cell = cell;
        this.takes = takes;
    }

    public static List<Unit> findByTeam(final Team team) {
        List<Cell> rowA = Cell.findByTeam(team);
        List<Unit> units = new ArrayList<>();
        for (Cell cell : rowA) {
            List<Take> takes = Take.findByCell(cell);
            units.add(new Unit(cell, takes));
        }
        return units;
    }
}
