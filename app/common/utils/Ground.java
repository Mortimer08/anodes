package common.utils;

import common.model.ground.Default;
import models.ground.*;

import java.util.List;

public class Ground {

    public static void create() {
        createTiers();
        createRows();
        createCells();
        createTakes();
        createAnodes();
    }

    private static void createTiers() {
        for (String name : Default.TIER_NAMES) {
            final Tier tier = new Tier();
            tier.name = name;
            tier.save();
        }
    }

    private static void createRows() {
        final List<Tier> tiers = Tier.findAll();
        for (Tier tier : tiers) {
            for (String rowName : Default.ROW_NAMES.get(tier.name)) {
                Row row = new Row();
                row.name = rowName;
                row.tier = tier;
                row.save();
            }
        }
    }

    private static void createCells() {
        final List<Row> rows = Row.findAll();
        for (Row row : rows) {
            for (int i = 0; i < Default.CELLS_IN_ROW; i++) {
                final Cell cell = new Cell(i, row);
                cell.setTeam(Default.getTeamByCell(cell));
            }
        }
    }

    private static void createTakes() {
        final List<Cell> cells = Cell.findAll();
        for (Cell cell : cells) {
            for (TakeNumber number : TakeNumber.values()) {
                final Take take = new Take(cell, number);
                take.save();
            }
        }
    }

    private static void createAnodes() {
        final List<Take> takes = Take.findAll();
        for (Take take : takes) {
            for (Integer number : take.number.anodes) {
                final Anode anode = new Anode(number, take);
                anode.save();
            }
        }
    }
}
