package controllers.ground.view;

import common.view.Field;

public enum CellField implements Field {
    NUMBER("number","Номер"),
    NAME("name", "Название"),
    ROW("row","Ряд"),
    CREATED("created", "Создано"),
    UPDATED("updated", "Изменено"),
    ;

    public final String name;
    public final String title;

    CellField(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
