package controllers.ground.view;

import common.view.Field;

public enum TierField implements Field {
    NAME("name", "Название"),
    CREATED("created", "Создано"),
    UPDATED("updated", "Изменено"),
    ;

    public final String name;
    public final String title;

    TierField(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
