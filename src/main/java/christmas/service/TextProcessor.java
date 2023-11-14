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

    // 해당 메서드를 조금 분리해야 할듯, 중복 검사는 ","로 구분한 배열 중복 확인 시도?
    public Map<String, Integer> processOrder(String orderMenus) {
        return Arrays.stream(orderMenus.split(MENU_SPLIT))
                .peek(menu -> validateOrderMenuInput(menu.trim()))
                .map(menuItemInfo ->
                        menuItemInfo.trim().split(ORDER_NUMBER_SPLIT))
                .collect(Collectors.toMap(
                        menuNames -> menuNames[0],
                        menuNumber -> parseInput(menuNumber[1], ExceptionMessage.INVALID_INPUT_ORDER),
                        (existing, replacement) -> {
                            throw new PromotionException(ExceptionMessage.INVALID_INPUT_ORDER);
                        }
                ));
    }

    private int parseInput(String input, ExceptionMessage exceptionMessage) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new PromotionException(exceptionMessage);
        }
    }

    private void validateOrderMenuInput(String input) {
        if(!input.matches(ORDER_MENU_INPUT_REGEX)) {
            throw new PromotionException(ExceptionMessage.INVALID_INPUT_ORDER);
        }
    }
}
