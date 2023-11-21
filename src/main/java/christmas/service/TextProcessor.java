package christmas.service;

import christmas.exception.ExceptionMessage;
import christmas.exception.PromotionException;

import java.util.*;
import java.util.stream.Collectors;

public class TextProcessor {
    private final static String MENU_SPLIT = ",";
    private final static String ORDER_NUMBER_SPLIT = "-";
    private final static String ORDER_MENU_INPUT_REGEX = "^[가-힣]+-\\d{1,2}$";

    public int parseInputDay(String input) {
        return parseInput(input, ExceptionMessage.INVALID_INPUT_DATE);
    }

    public Map<String, Integer> processOrder(String orderMenus) {
        List<String> menuArray = extractMenus(orderMenus);

        return createOrderMap(menuArray);
    }

    private int parseInput(String input, ExceptionMessage exceptionMessage) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new PromotionException(exceptionMessage);
        }
    }

    private List<String> extractMenus(String inputOrderMenus) {
        return Arrays.stream(inputOrderMenus.split(MENU_SPLIT))
                .map(String::trim)
                .peek(this::validateOrderMenuInput)
                .toList();
    }

    private Map<String, Integer> createOrderMap(List<String> orderMenus) {
        return orderMenus.stream()
                .map(menuItemInfo -> menuItemInfo.split(ORDER_NUMBER_SPLIT))
                .collect(Collectors.toUnmodifiableMap(
                        menuItemSplit -> menuItemSplit[0].trim(),
                        menuItemSplit -> parseInput(menuItemSplit[1].trim(), ExceptionMessage.INVALID_INPUT_ORDER),
                        (existing, replacement) -> {
                            throw new PromotionException(ExceptionMessage.INVALID_INPUT_ORDER);
                        }
                ));
    }

    private void validateOrderMenuInput(String input) {
        if (!input.matches(ORDER_MENU_INPUT_REGEX)) {
            throw new PromotionException(ExceptionMessage.INVALID_INPUT_ORDER);
        }
    }
}
