package christmas.model.event;

import christmas.model.date.ReservationDate;

import java.util.Arrays;

public class ReservationDateEvent {
    private static final int WEEK = 7;
    private static final int FRIDAY = 2;
    private static final int SATURDAY = 3;

    private final ReservationDate reservationDate;
    private final ChristmasDiscount christmasDiscount;
    private final DiscountDateWeek discountDateWeek;
    private final SpecialDiscountDate specialDiscountDate;

    public ReservationDateEvent(int day) {
        this.reservationDate = new ReservationDate(day);
        this.christmasDiscount = new ChristmasDiscount(reservationDate);
        this.discountDateWeek = initDiscountDateWeek(reservationDate);
        this.specialDiscountDate = initSpecialDiscountDate(reservationDate);
    }

    private DiscountDateWeek initDiscountDateWeek(ReservationDate reservationDate) {
        if (reservationDate.day() % WEEK == FRIDAY ||
                reservationDate.day() % WEEK == SATURDAY) {

            return DiscountDateWeek.WEEKEND;
        }

        return DiscountDateWeek.WEEKDAY;
    }

    private SpecialDiscountDate initSpecialDiscountDate(ReservationDate reservationDate) {
        return Arrays.stream(SpecialDiscountDate.values())
                .filter(specialDay ->
                        reservationDate.day() == specialDay.getDay())
                .findFirst()
                .orElse(SpecialDiscountDate.OTHER_DAY);
    }

    public ReservationDate getReservationDate() {
        return reservationDate;
    }

    public ChristmasDiscount getChristmasDiscount() {
        return christmasDiscount;
    }

    public DiscountDateWeek getDiscountDateWeek() {
        return discountDateWeek;
    }

    public SpecialDiscountDate getSpecialDiscountDate() {
        return specialDiscountDate;
    }
}
