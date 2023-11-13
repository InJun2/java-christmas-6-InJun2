package christmas.model.event.dto;

import christmas.model.event.ReservationDateEvent;

public class ReservationDateEventDto {
    private int day;
    private int christmasDiscount;
    private String dateWeek;
    private int specialDiscountDate;

    public ReservationDateEventDto(ReservationDateEvent event) {
        this.day = event.getReservationDate().day();
        this.christmasDiscount = event.getChristmasDiscount().getDiscount();
        this.dateWeek = event.getDiscountDateWeek().getDateType();
        this.specialDiscountDate = event.getSpecialDiscountDate().getDiscountPrice();
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

    public int getSpecialDiscountDate() {
        return specialDiscountDate;
    }
}
