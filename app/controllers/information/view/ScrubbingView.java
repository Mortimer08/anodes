package controllers.information.view;

public enum ScrubbingView {

    HAPPENED("happened","Дата"),
    TAKE("take","Подъем"),
    MACHINED("machined","W703"),
    FIRST_DAMAGE("firstDamage","<10%"),
    TO_CHANGE("toChange","На замену"),
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
