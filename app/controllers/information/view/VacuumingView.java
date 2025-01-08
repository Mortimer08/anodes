package controllers.information.view;

public enum VacuumingView {

    HAPPENED("happened","Дата"),
    CELL("cell","Ванна"),
    COMMENT("comment","Примечание"),
    ;

    public String name;
    public String title;

    VacuumingView(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
