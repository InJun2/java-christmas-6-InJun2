package christmas.model.event;

import christmas.model.date.ReservationDate;

public class ChristmasDiscount {
    private static final int FIRST_DAY = 1;
    private static final int CHRISTMAS_DAY = 25;
    private static final int BASIC_DISCOUNT = 1000;
    private static final int PLUS_DISCOUNT = 100;
    private static final int NONE_DISCOUNT = 0;

    private final int discount;

    public ChristmasDiscount(ReservationDate date) {
        this.discount = initDiscountAmount(date);
    }

    private int initDiscountAmount(ReservationDate date) {
        if (date.day() > CHRISTMAS_DAY) {
            return NONE_DISCOUNT;
        }

        return BASIC_DISCOUNT + getPlusDiscount(date.day());
    }

    private int getPlusDiscount(int day) {
        return (day - FIRST_DAY) * PLUS_DISCOUNT;
    }

    public int getDiscount() {
        return discount;
    }
}
