package models.auth;

public enum UserRole {
    USER("user", "Пользователь"),
    ADMINISTRATOR("user", "Администратор клиента"),
    HEAD("head", "Руководитель"),
    ADMIN("administrator", "Администратор"),

    ;
    public final String name;
    public final String title;

    UserRole(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
