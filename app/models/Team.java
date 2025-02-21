package models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Team {

    TEAM_1("Бригада 1", 1),
    TEAM_2("Бригада 2", 2),
    TEAM_3("Бригада 3", 3),
    TEAM_4("Бригада 4", 4),
    TEAM_5("Бригада 5", 5),
    ;

    public String name;
    public Integer number;

    Team(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public static Team getTeam(int number) {
        return Team.values()[number - 1];
    }

    public static List<Integer> getIndexes() {
        return Arrays.stream(Team.values()).map(Enum::ordinal).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return name;
    }
}
