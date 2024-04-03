package models.ground;

public enum TakeNumber {
    FIRST(1, "Первый подъём", 21),
    SECOND(2, "Второй подъём", 20),
    THIRD(3, "Третий подъём", 20),
    FOURTH(4, "Четвертый подъём", 20),
    ;
    public Integer number;
    public String title;
    public Integer quantity;

    TakeNumber(Integer number, String title, Integer quantity) {
        this.number = number;
        this.title = title;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return title;
    }
}
