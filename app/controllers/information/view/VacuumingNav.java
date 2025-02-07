package controllers.information.view;

public enum VacuumingNav {

    DETAILS("/vacuuming/view/%s","Подробности"),
    SETTINGS("/vacuuming/show/%s","Изменить"),
    DELETE("/vacuuming/delete/%s","Удалить"),
    ;

    public String url;
    public String title;

    VacuumingNav(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl(final long id) {
        return String.format(url, String.valueOf(id));
    }

    @Override
    public String toString() {
        return title;
    }
}
