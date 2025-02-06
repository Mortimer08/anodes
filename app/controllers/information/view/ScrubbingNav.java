package controllers.information.view;

public enum ScrubbingNav {

    DETAILS("/scrubbing/view/%s","Подробности"),
    SETTINGS("/scrubbing/show/%s","Изменить"),
    DELETE("/scrubbing/delete/%s","Удалить"),
    ;

    public String url;
    public String title;

    ScrubbingNav(String url, String title) {
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
