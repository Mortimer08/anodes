package models.ground;

import java.util.Arrays;

public enum TakeNumber {

    FIRST(1, "Первый подъём", 21, new Integer[]{1, 5, 9, 13, 17, 21, 25, 29, 33, 37, 41, 45, 49, 53, 57, 61, 65, 69, 73, 77, 81}),
    SECOND(2, "Второй подъём", 20, new Integer[]{2, 6, 10, 14, 18, 22, 26, 30, 34, 38, 42, 46, 50, 54, 58, 62, 66, 70, 74, 78}),
    THIRD(3, "Третий подъём", 20, new Integer[]{3, 7, 11, 15, 19, 23, 27, 31, 35, 39, 43, 47, 51, 55, 59, 63, 67, 71, 75, 79}),
    FOURTH(4, "Четвертый подъём", 20, new Integer[]{4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80}),
    ;
    public Integer number;
    public String title;
    public Integer quantity;
    public Integer[] anodes;

    TakeNumber(Integer number, String title, Integer quantity, Integer[] anodes) {
        this.number = number;
        this.title = title;
        this.quantity = quantity;
        this.anodes = anodes;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    public static TakeNumber takeNumberByAnode(Integer anode) {
        if (Arrays.stream(FIRST.anodes).anyMatch(anode::equals)) return FIRST;
        else if (Arrays.stream(SECOND.anodes).anyMatch(anode::equals)) return SECOND;
        else if (Arrays.stream(THIRD.anodes).anyMatch(anode::equals)) return THIRD;
        else if (Arrays.stream(FOURTH.anodes).anyMatch(anode::equals)) return FOURTH;
        return null;
    }

    public static Integer getQuantity(final Integer number) {
        return TakeNumber.values()[number - 1].quantity;
    }
}
