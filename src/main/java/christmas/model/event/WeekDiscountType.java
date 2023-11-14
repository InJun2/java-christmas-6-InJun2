package christmas.model.event;

import christmas.exception.ExceptionMessage;
import christmas.exception.PromotionException;
import christmas.model.menu.MenuCategory;

import java.util.Arrays;

public enum WeekDiscountType {
    WEEKDAY("평일 할인", MenuCategory.DESSERT, 2023),
    WEEKEND("주말 할인", MenuCategory.MAIN_COURSE, 2023),
    ;

    private final String dateType;
    private final MenuCategory discountCategory;
    private final int discountPrice;

    WeekDiscountType(String dateType, MenuCategory discountCategory,
                     int discountPrice) {
        this.dateType = dateType;
        this.discountCategory = discountCategory;
        this.discountPrice = discountPrice;
    }

    public static WeekDiscountType checkWeekType(String type) {
        return Arrays.stream(WeekDiscountType.values())
                .filter(weekType ->
                        weekType.getDateType().equals(type))
                .findFirst()
                .orElseThrow(() ->
                        new PromotionException(ExceptionMessage.INVALID_INPUT_ORDER));
    }

    public String getDateType() {
        return dateType;
    }

    public MenuCategory getDiscountCategory() {
        return discountCategory;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
}
