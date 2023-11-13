package christmas.model.service;

import christmas.exception.ExceptionMessage;
import christmas.exception.PromotionException;

import java.util.*;
import java.util.stream.Collectors;

public class TextProcessor {
    private final static String MENU_SPLIT = ",";
    private final static String ORDER_NUMBER_SPLIT = "-";

    public int parseInputDay(String input) {
        return parseInput(input);
    }

    public Map<String, Integer> processOrder(String[] orderMenus) {
        return Arrays.stream(orderMenus)
                .flatMap(orderMenu ->
                        Arrays.stream(orderMenu.split(MENU_SPLIT)))
                .map(menuItemInfo ->
                        menuItemInfo.trim().split(ORDER_NUMBER_SPLIT))
                .collect(Collectors.toMap(
                        parts -> parts[0],
                        parts -> parseInput(parts[1]),
                        (existing, replacement) -> {
                            throw new PromotionException(ExceptionMessage.INVALID_INPUT_ORDER);
                        }
                ));
    }
    // 해당 예외는 도메인에서 유효성 검사 - 메뉴 이름이 없을경우, 메뉴 중복, 정수가 잘못되었을 때, 총 주문수가 20개가 넘을 때

    private int parseInput(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new PromotionException(ExceptionMessage.INVALID_INTEGER_FORMAT);
        }
    }
}
