package models;

public enum Team {

    TEAM_1("Бригада 1"),
    TEAM_2("Бригада 2"),
    TEAM_3("Бригада 3"),
    TEAM_4("Бригада 4"),
    TEAM_5("Бригада 5"),
    ;

    public String name;

    Team(String name) {
        this.name = name;
    }

    public static Team getTeam(int number) {
        return Team.values()[number - 1];
    }


}
