package christmas.model.date;

import christmas.exception.ExceptionMessage;
import christmas.exception.PromotionException;

public record ReservationDate(int day) {
    private static final int FIRST_DAY = 1;
    private static final int LAST_DAY = 31;

    public ReservationDate {
        validateDay(day);
    }

    private void validateDay(int day) {
        if (day < FIRST_DAY || day > LAST_DAY) {
            throw new PromotionException(ExceptionMessage.INVALID_INPUT_DATE);
        }
    }
}
