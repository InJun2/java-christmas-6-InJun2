package christmas.model.event;

import christmas.model.date.ReservationDate;

import java.util.Arrays;

public class ReservationDateEvent {
    private static final int WEEK = 7;
    private static final int FRIDAY = 2;
    private static final int SATURDAY = 3;

    private final ReservationDate reservationDate;
    private final ChristmasDiscount christmasDiscount;
    private final WeekDiscountType discountDateWeek;
    private final SpecialDayDiscount specialDiscountDate;

    public ReservationDateEvent(int day) {
        this.reservationDate = new ReservationDate(day);
        this.christmasDiscount = new ChristmasDiscount(reservationDate);
        this.discountDateWeek = initDiscountDateWeek(reservationDate);
        this.specialDiscountDate = initSpecialDiscountDate(reservationDate);
    }

    private WeekDiscountType initDiscountDateWeek(ReservationDate reservationDate) {
        if (reservationDate.day() % WEEK == FRIDAY ||
                reservationDate.day() % WEEK == SATURDAY) {

            return WeekDiscountType.WEEKEND;
        }

        return WeekDiscountType.WEEKDAY;
    }

    private SpecialDayDiscount initSpecialDiscountDate(ReservationDate reservationDate) {
        return Arrays.stream(SpecialDayDiscount.values())
                .filter(specialDay ->
                        reservationDate.day() == specialDay.getDay())
                .findFirst()
                .orElse(SpecialDayDiscount.OTHER_DAY);
    }

    public ReservationDate getReservationDate() {
        return reservationDate;
    }

    public ChristmasDiscount getChristmasDiscount() {
        return christmasDiscount;
    }

    public WeekDiscountType getDiscountDateWeek() {
        return discountDateWeek;
    }

    public SpecialDayDiscount getSpecialDiscountDate() {
        return specialDiscountDate;
    }
}
