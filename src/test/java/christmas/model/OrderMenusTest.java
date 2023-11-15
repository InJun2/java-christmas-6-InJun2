package christmas.model;

import christmas.exception.ExceptionMessage;
import christmas.model.menu.OrderMenus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderMenusTest {
    @ParameterizedTest
    @MethodSource("createSuccessOrderMenusData")
    @DisplayName("주문 메뉴 내역 객체 정상 생성")
    void createOrderMenus(Map<String, Integer> inputData) {
        assertThat(new OrderMenus(inputData))
                .isInstanceOf(OrderMenus.class);
    }

    private static Stream<Arguments> createSuccessOrderMenusData() {
        return Stream.of(
                Arguments.of(
                        Map.of("타파스", 2, "바비큐립", 3, "제로콜라", 2, "샴페인", 2)
                ),
                Arguments.of(
                        Map.of("시저샐러드", 4, "해산물파스타", 4, "초코케이크", 2, "제로콜라", 4)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("invalidOrderMenuNameData")
    @DisplayName("주문 메뉴 내역 객체 이름 불일치 예외 발생")
    void invalidOrderMenuName(Map<String, Integer> inputData) {
        assertThatThrownBy(() -> new OrderMenus(inputData))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_INPUT_ORDER.getErrorMessage());
    }

    private static Stream<Arguments> invalidOrderMenuNameData() {
        return Stream.of(
                Arguments.of(
                        Map.of("양송이슈프", 2, "티본스테이크", 4, "aa123", 2)
                ),
                Arguments.of(
                        Map.of("타파스", 2, "치킨", 3, "탕후루", 2, "샴페인", 2)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("invalidOrderMenuCountData")
    @DisplayName("주문 메뉴 내역 객체 단일 주문수 예외 발생")
    void invalidOrderMenuCount(Map<String, Integer> inputData) {
        assertThatThrownBy(() -> new OrderMenus(inputData))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_INPUT_ORDER.getErrorMessage());
    }

    private static Stream<Arguments> invalidOrderMenuCountData() {
        return Stream.of(
                Arguments.of(
                        Map.of("양송이수프", 0, "티본스테이크", 4, "샴페인", 2)
                ),
                Arguments.of(
                        Map.of("타파스", -2, "바비큐립", 3, "제로콜라", 2, "샴페인", 2)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("invalidOrderMenuTotalCountData")
    @DisplayName("주문 메뉴 내역 객체 총 주문수 20개 초과 예외 발생")
    void invalidOrderMenuAllCount(Map<String, Integer> inputData) {
        assertThatThrownBy(() -> new OrderMenus(inputData))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_INPUT_ORDER.getErrorMessage());
    }

    private static Stream<Arguments> invalidOrderMenuTotalCountData() {
        return Stream.of(
                Arguments.of(
                        Map.of("양송이수프", 8, "티본스테이크", 8, "샴페인", 8)
                ),
                Arguments.of(
                        Map.of("타파스", 5, "바비큐립", 5, "제로콜라", 6, "샴페인", 5)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("invalidOrderMenuByAllBeverageData")
    @DisplayName("주문 메뉴 내역 객체 음료만 주문시 예외 발생")
    void invalidOrderMenuByAllBeverage(Map<String, Integer> inputData) {
        assertThatThrownBy(() -> new OrderMenus(inputData))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_INPUT_ORDER.getErrorMessage());
    }

    private static Stream<Arguments> invalidOrderMenuByAllBeverageData() {
        return Stream.of(
                Arguments.of(
                        Map.of("제로콜라", 1, "샴페인", 1)
                ),
                Arguments.of(
                        Map.of("제로콜라", 3, "샴페인", 3, "레드와인", 3)
                )
        );
    }
}
