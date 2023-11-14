package christmas.model.event;

import christmas.model.event.dto.ReservationDateEventDTO;

public class DiscountDetail {
    private final int totalPriceBeforeDiscount;
    private final int totalPriceAfterDiscount;
    private final WeekDiscountType weekDiscountType;
    private final int christmasDiscount;
    private final int weekTypeDiscount;
    private final int specialDiscount;

    public DiscountDetail(ReservationDateEventDTO dateEventDTO, int totalPriceBeforeDiscount, int weekTypeDiscount) {
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.weekDiscountType = WeekDiscountType.checkWeekType(dateEventDTO.getDateWeek());
        this.christmasDiscount = dateEventDTO.getChristmasDiscount();
        this.specialDiscount = dateEventDTO.getSpecialDiscount();
        this.weekTypeDiscount = weekTypeDiscount;
        this.totalPriceAfterDiscount = initTotalPriceAfterDiscount();
    }



    private int initTotalPriceAfterDiscount() {
        return totalPriceBeforeDiscount - getTotalDiscount();
    }

    public int getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public int getTotalPriceAfterDiscount() {
        return totalPriceAfterDiscount;
    }

    public int getChristmasDiscount() {
        return christmasDiscount;
    }

    public int getSpecialDiscount() {
        return specialDiscount;
    }

    public int getWeekTypeDiscount() {
        return weekTypeDiscount;
    }

    public WeekDiscountType getWeekDiscountType() {
        return weekDiscountType;
    }

    public int getTotalDiscount() {
        return christmasDiscount + weekTypeDiscount + specialDiscount;
    }
}
