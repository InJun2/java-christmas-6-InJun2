package christmas.model.menu;

import christmas.exception.ExceptionMessage;
import christmas.exception.PromotionException;

import java.util.Arrays;

public enum MenuItem {
    APPETIZER_MUSHROOM_SOUP("양송이수프", 6_000, MenuCategory.APPETIZER),
    APPETIZER_TAPAS("타파스", 5_500, MenuCategory.APPETIZER),
    APPETIZER_CAESAR_SALAD("시저샐러드", 8_000, MenuCategory.APPETIZER),

    MAIN_T_BONE_STEAK("티본스테이크", 55_000, MenuCategory.MAIN_COURSE),
    MAIN_BBQ_RIB("바비큐립", 54_000, MenuCategory.MAIN_COURSE),
    MAIN_SEAFOOD_PASTA("해산물파스타", 35_000, MenuCategory.MAIN_COURSE),
    MAIN_CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuCategory.MAIN_COURSE),

    DESSERT_CHOCO_CAKE("초코케이크", 15_000, MenuCategory.DESSERT),
    DESSERT_ICE_CREAM("아이스크림", 5_000, MenuCategory.DESSERT),

    BEVERAGE_ZERO_COLA("제로콜라", 3_000, MenuCategory.BEVERAGE),
    BEVERAGE_RED_WINE("레드와인", 60_000, MenuCategory.BEVERAGE),
    BEVERAGE_CHAMPAGNE("샴페인", 25_000, MenuCategory.BEVERAGE),
    ;

    private final String itemName;
    private final int itemPrice;
    private final MenuCategory menuCategory;

    MenuItem(String itemName, int itemPrice, MenuCategory menuCategory) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.menuCategory = menuCategory;
    }

    public static MenuItem findMenuItemByName(String itemName, ExceptionMessage message) {
        return Arrays.stream(MenuItem.values())
                .filter(menu -> menu.getItemName().equals(itemName))
                .findFirst()
                .orElseThrow(() ->
                        new PromotionException(message));
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }
}
