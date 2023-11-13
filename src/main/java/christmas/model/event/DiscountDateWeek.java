package christmas.model.event;

import christmas.model.menu.MenuCategory;

public enum DiscountDateWeek {
    WEEKDAY("평일 할인", MenuCategory.DESSERT, 2023),
    WEEKEND("주말 할인", MenuCategory.MAIN_COURSE, 2023),
    ;

    private final String dateType;
    private final MenuCategory discountCategory;
    private final int discountPrice;

    DiscountDateWeek(String dateType, MenuCategory discountCategory,
                     int discountPrice) {
        this.dateType = dateType;
        this.discountCategory = discountCategory;
        this.discountPrice = discountPrice;
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
