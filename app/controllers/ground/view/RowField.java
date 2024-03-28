package controllers.ground.view;

import common.view.Field;

public enum RowField implements Field {
    NAME("name", "Название"),
    TIER("tier","Серия"),
    CREATED("created", "Создано"),
    UPDATED("updated", "Изменено"),
    ;

    public final String name;
    public final String title;

    RowField(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
