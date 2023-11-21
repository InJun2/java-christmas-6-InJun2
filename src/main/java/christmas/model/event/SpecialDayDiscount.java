package christmas.model.event;

public enum SpecialDayDiscount {
    THIRD_DAY(3, 1000),
    TENTH_DAY(10, 1000),
    SEVENTEENTH_DAY(17, 1000),
    TWENTY_FOURTH_DAY(24, 1000),
    TWENTY_FIFTH_DAY(25, 1000),
    THIRTY_FIRST_DAY(31, 1000),
    OTHER_DAY(0, 0),
    ;

    private final int day;
    private final int discountPrice;

    SpecialDayDiscount(int day, int discountPrice) {
        this.day = day;
        this.discountPrice = discountPrice;
    }

    public int getDay() {
        return day;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
}
