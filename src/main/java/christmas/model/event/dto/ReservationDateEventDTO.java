package christmas.model.event.dto;

import christmas.model.event.ReservationDateEvent;

public class ReservationDateEventDTO {
    private final int day;
    private final int christmasDiscount;
    private final String dateWeek;
    private final int specialDiscount;

    public ReservationDateEventDTO(ReservationDateEvent event) {
        this.day = event.getReservationDate().day();
        this.christmasDiscount = event.getChristmasDiscount().getDiscount();
        this.dateWeek = event.getDiscountDateWeek().getDateType();
        this.specialDiscount = event.getSpecialDiscountDate().getDiscountPrice();
    }

    public int getDay() {
        return day;
    }

    public int getChristmasDiscount() {
        return christmasDiscount;
    }

    public String getDateWeek() {
        return dateWeek;
    }

    public int getSpecialDiscount() {
        return specialDiscount;
    }
}
