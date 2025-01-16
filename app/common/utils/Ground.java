package common.utils;

import common.model.ground.Default;
import models.ground.*;
import play.Logger;

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
        Logger.info("create default tiers");
        for (String name : Default.TIER_NAMES) {
            final Tier tier = new Tier();
            tier.name = name;
            tier.active = true;
            tier.save();
        }
    }

    private static void createRows() {
        Logger.info("create default rows");
        final List<Tier> tiers = Tier.findAll();
        for (Tier tier : tiers) {
            for (String rowName : Default.ROW_NAMES.get(tier.name)) {
                Row row = new Row();
                row.name = rowName;
                row.tier = tier;
                row.active = true;
                row.save();
            }
        }
    }

    private static void createCells() {
        Logger.info("create default cells");
        final List<Row> rows = Row.findAll();
        for (Row row : rows) {
            for (int i = 1; i < Default.CELLS_IN_ROW; i++) {
                final Cell cell = new Cell(i, row);
                cell.create();
                cell.setTeam(Default.getTeamByCell(cell));
                cell.active = true;
                cell.save();
            }
        }
    }

    private static void createTakes() {
        Logger.info("create default takes");
        final List<Cell> cells = Cell.findAll();
        for (Cell cell : cells) {
            for (TakeNumber number : TakeNumber.values()) {
                final Take take = new Take(cell, number);
                take.active = true;
                take.save();
            }
        }
    }

    private static void createAnodes() {
        final List<Take> takes = Take.findAll();
        for (Take take : takes) {
            for (Integer number : take.number.anodes) {
                final Anode anode = new Anode(number, take);
                anode.active = true;
                anode.save();
            }
        }
    }
}
