package common.model.ground;

import models.Team;
import models.ground.Cell;
import play.Logger;

import java.util.Arrays;
import java.util.Map;

public class Default {

    public static final String[] TIER_NAMES = {"I", "II"};
    public static final String[] TIER_I_ROW_NAMES = {"A", "B"};
    public static final String[] TIER_II_ROW_NAMES = {"C", "D"};
    public static final Map<String, String[]> ROW_NAMES = Map.of(
            TIER_NAMES[0], TIER_I_ROW_NAMES,
            TIER_NAMES[1], TIER_II_ROW_NAMES);
    public static final Integer CELLS_IN_ROW = 51;
    public static final Integer ANODES_IN_CELL = 81;
    public static final Integer TAKES_IN_CELL = 4;
    public static final Map<String, Integer[]> TEAM3_CELLS = Map.of(
            TIER_I_ROW_NAMES[0],
            new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51});
    public static final Map<String, Integer[]> TEAM4_CELLS = Map.of(
            TIER_I_ROW_NAMES[1],
            new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51});
    public static final Map<String, Integer[]> TEAM1_CELLS = Map.of(
            TIER_II_ROW_NAMES[0],
            new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51});
    public static final Map<String, Integer[]> TEAM2_CELLS = Map.of(
            TIER_II_ROW_NAMES[1],
            new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51});
    public static final Map<String, Integer[]> TEAM5_CELLS = Map.of(
            TIER_I_ROW_NAMES[0],
            new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            TIER_I_ROW_NAMES[1],
            new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            TIER_II_ROW_NAMES[0],
            new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
            TIER_II_ROW_NAMES[1],
            new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

    public static Team getTeamByCell(final Cell cell) {
        if (cellMatch(TEAM1_CELLS, cell)) {
            return Team.TEAM_1;
        } else if (cellMatch(TEAM2_CELLS, cell)) {
            return Team.TEAM_2;
        } else if (cellMatch(TEAM3_CELLS, cell)) {
            return Team.TEAM_3;
        } else if (cellMatch(TEAM4_CELLS, cell)) {
            return Team.TEAM_4;
        } else if (cellMatch(TEAM5_CELLS, cell)) {
            return Team.TEAM_5;
        }
        return null;
    }

    public static boolean cellMatch(final Map<String, Integer[]> cells, final Cell cell) {
        return rowMatch(cells, cell) && numberMatch(cells, cell);
    }

    private static boolean rowMatch(final Map<String, Integer[]> cells, final Cell cell) {
        return cells.get(cell.row.name) != null;
    }

    private static boolean numberMatch(final Map<String, Integer[]> cells, final Cell cell) {
        return Arrays.asList(cells.get(cell.row.name)).contains(cell.number);
    }
}
