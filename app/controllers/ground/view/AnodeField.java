package controllers.ground.view;

public enum AnodeField {
    NUMBER("number","Номер"),
    NAME("name", "Название"),
    TAKE("take","Подъём"),
    CELL("cell","Ванна"),
    CREATED("created", "Создано"),
    UPDATED("updated", "Изменено"),
            ;

    public final String name;
    public final String title;

    AnodeField(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
