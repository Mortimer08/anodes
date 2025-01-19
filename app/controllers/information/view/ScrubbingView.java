package controllers.information.view;

public enum ScrubbingView {

    HAPPENED("happened","Дата"),
    TAKE("take","Подъем"),
    FIRST_DAMAGE("firstDamage","<10%"),
    TO_CHANGE("toChange","На замену"),
    MACHINED("machined","W703"),
    HANDLED("handled","Вручную"),
    CHANGED("changed","Заменили"),
    COMMENT("comment","Примечание"),
    ;

    public String name;
    public String title;

    ScrubbingView(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
