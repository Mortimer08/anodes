package controllers.ground.view;

import common.view.Field;

public enum TakeField implements Field {
    NUMBER("number","Номер"),
    NAME("name", "Название"),
    CELL("cell","Ванна"),
    CREATED("created", "Создано"),
    UPDATED("updated", "Изменено"),
    ;

    public final String name;
    public final String title;

    TakeField(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
