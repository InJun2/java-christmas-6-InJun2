package christmas.model.menu;

import christmas.exception.ExceptionMessage;
import christmas.exception.PromotionException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderMenus {
    private static final int MIN_ORDER_MENU_NUMBER = 1;
    private static final int MAX_ORDER_MENUS_NUMBER = 20;
    private static final int MIN_ORDER_TOTAL_AMOUNT = 10000;

    private final Map<MenuItem, Integer> orderMenus;

    public OrderMenus(Map<String, Integer> orderMenus) {
        this.orderMenus = validateOrderMenusByNames(orderMenus);
        validateOrderMenus();
    }

    private Map<MenuItem, Integer> validateOrderMenusByNames(Map<String, Integer> orderMenus) {
        return generateOrderMenuValues(orderMenus)
                .collect(Collectors.toUnmodifiableMap(
                        entry -> MenuItem.findMenuItemByName(entry.getKey()),
                        Map.Entry::getValue
                ));
    }

    private void validateOrderMenus() {
        validateOrderNumbers();
        validateTotalOrderNumbers();
        validateOrderAllBeverage();
        validateTotalOrderAmount();
    }

    private void validateOrderNumbers() {
        generateOrderMenuValues(orderMenus)
                .filter(menu ->
                        menu.getValue() < MIN_ORDER_MENU_NUMBER)
                .findAny()
                .ifPresent(menuName -> throwInvalidOrderException());
    }

    private void validateTotalOrderNumbers() {
        int orderNumber = generateOrderMenuValues(orderMenus)
                .mapToInt(Map.Entry::getValue)
                .sum();

        if (orderNumber > MAX_ORDER_MENUS_NUMBER) {
            throwInvalidOrderException();
        }
    }

    private void validateOrderAllBeverage() {
        boolean allBeverage = generateOrderMenuValues(orderMenus)
                .allMatch(orderMenu ->
                        orderMenu.getKey().getMenuCategory() == MenuCategory.BEVERAGE);

        if (allBeverage) {
            throwInvalidOrderException();
        }
    }

    private void validateTotalOrderAmount() {
        int totalPrice = generateOrderMenuValues(orderMenus)
                .mapToInt(orderMenu ->
                        orderMenu.getKey().getItemPrice() * orderMenu.getValue())
                .sum();

        if (totalPrice < MIN_ORDER_TOTAL_AMOUNT) {
            throwInvalidOrderException();
        }
    }

    private <K, V> Stream<Map.Entry<K, V>> generateOrderMenuValues(Map<K, V> orderMenus) {
        return orderMenus.entrySet()
                .stream();
    }

    private void throwInvalidOrderException() {
        throw new PromotionException(ExceptionMessage.INVALID_INPUT_ORDER);
    }

    public Map<String, Integer> getOrderMenus() {
        Map<String, Integer> menuNames = new HashMap<>();
        orderMenus.forEach((menuItem, quantity) ->
                menuNames.put(menuItem.getItemName(), quantity));

        return Collections.unmodifiableMap(menuNames);
    }
}
