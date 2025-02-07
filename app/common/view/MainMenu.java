package common.view;

import models.auth.User;

import java.util.EnumSet;

public enum MainMenu {

    CLEANING_TEAM_1("", "Бригада 1", "/cleaning/team/1"),
    CLEANING_TEAM_2("", "Бригада 2", "/cleaning/team/2"),
    CLEANING_TEAM_3("", "Бригада 3", "/cleaning/team/3"),
    CLEANING_TEAM_4("", "Бригада 4", "/cleaning/team/4"),
    CLEANING_TEAM_5("", "Бригада 5", "/cleaning/team/5"),
    VACUUMINGS("", "Крутежка", "/vacuuming"),
    SCRUBBINGS("", "Подъемы", "/scrubbing"),
    TOTALS("", "Показатели", "/total"),
    RESULTS("", "Результаты", "/result"),
    EXIT("", "Выход", "/logout"),
    ;

    public String name;
    public String title;
    public String url;

    MainMenu(String name, String title, String url) {
        this.name = name;
        this.title = title;
        this.url = url;
    }

    public static EnumSet<MainMenu> getMenu(final User user) {
        if (user.superuser) {
            return EnumSet.of(
                    CLEANING_TEAM_1,
                    CLEANING_TEAM_2,
                    CLEANING_TEAM_3,
                    CLEANING_TEAM_4,
                    CLEANING_TEAM_5,
                    VACUUMINGS,
                    SCRUBBINGS,
                    TOTALS,
                    RESULTS,
                    EXIT
            );
        } else {
            return EnumSet.of(
                    CLEANING_TEAM_1,
                    CLEANING_TEAM_2,
                    CLEANING_TEAM_3,
                    CLEANING_TEAM_4,
                    CLEANING_TEAM_5,
                    VACUUMINGS,
                    SCRUBBINGS,
                    EXIT
            );
        }
    }

    public static MainMenu getTeam(final Integer number) {
        if (number == 1) {
            return CLEANING_TEAM_1;
        }
        if (number == 2) {
            return CLEANING_TEAM_2;
        }
        if (number == 3) {
            return CLEANING_TEAM_3;
        }
        if (number == 4) {
            return CLEANING_TEAM_4;
        }
        if (number == 5) {
            return CLEANING_TEAM_5;
        }
        return null;
    }
}
