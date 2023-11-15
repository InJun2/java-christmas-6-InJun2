package christmas.model;

import christmas.model.event.*;
import christmas.model.event.dto.ReservationDateEventDTO;
import christmas.model.menu.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EventResultTest {
    private Map<MenuItem, Integer> orderMenus;
    private ReservationDateEventDTO eventDTO;

    @BeforeEach
    void setUp() {
        orderMenus = new HashMap<>();
        eventDTO = mock(ReservationDateEventDTO.class);
    }

    @ParameterizedTest
    @MethodSource("createNormalOrderMenusData")
    @DisplayName("평일 이벤트 적용 결과 객체 생성")
    void createEventResultByWeekday(Map<MenuItem, Integer> testMap) {
        orderMenus = testMap;

        setEventDTOFromNoneSpecialDay();
        when(eventDTO.getDateWeek()).thenReturn(WeekDiscountType.WEEKDAY.getDateType());

        EventResult testResult = new EventResult(eventDTO, orderMenus);
        assertThat(testResult.getDiscountDetail().getTotalDiscount())
                .isEqualTo(1600);
        assertThat(testResult.getGiftMenu())
                .isEqualTo(GiftMenu.CHAMPAGNE);
        assertThat(testResult.getRewardBadge())
                .isEqualTo(RewardBadge.SANTA);
    }

    private void setEventDTOFromNoneSpecialDay() {
        when(eventDTO.getDay()).thenReturn(7);
        when(eventDTO.getChristmasDiscount()).thenReturn(1600);
        when(eventDTO.getSpecialDiscount()).thenReturn(0);
    }

    @ParameterizedTest
    @MethodSource("createNormalOrderMenusData")
    @DisplayName("주말 이벤트 적용 결과 객체 생성")
    void createEventResultByWeekend(Map<MenuItem, Integer> testMap) {
        orderMenus = testMap;

        setEventDTOFromNoneSpecialDay();
        when(eventDTO.getDateWeek()).thenReturn(WeekDiscountType.WEEKEND.getDateType());

        EventResult testResult = new EventResult(eventDTO, orderMenus);
        assertThat(testResult.getDiscountDetail().getTotalDiscount())
                .isEqualTo(9692);
        assertThat(testResult.getGiftMenu())
                .isEqualTo(GiftMenu.CHAMPAGNE);
        assertThat(testResult.getRewardBadge())
                .isEqualTo(RewardBadge.SANTA);
    }

    @ParameterizedTest
    @MethodSource("createNormalOrderMenusData")
    @DisplayName("특별 할인 이벤트 적용 결과 객체 생성")
    void createEventResultBySpecialDay(Map<MenuItem, Integer> testMap) {
        orderMenus = testMap;

        setEventDTOFromAllEvent();

        EventResult testResult = new EventResult(eventDTO, orderMenus);
        assertThat(testResult.getDiscountDetail().getTotalDiscount())
                .isEqualTo(4400);
    }

    private void setEventDTOFromAllEvent() {
        when(eventDTO.getDay()).thenReturn(25);
        when(eventDTO.getChristmasDiscount()).thenReturn(3400);
        when(eventDTO.getDateWeek()).thenReturn(WeekDiscountType.WEEKDAY.getDateType());
        when(eventDTO.getSpecialDiscount()).thenReturn(1000);
    }

    @ParameterizedTest
    @MethodSource("createNonDiscountOrderMenusData")
    @DisplayName("모든 이벤트 미적용 결과 객체 생성")
    void createEventResultByNonDiscount(Map<MenuItem, Integer> testMap) {
        orderMenus = testMap;

        when(eventDTO.getDay()).thenReturn(26);
        when(eventDTO.getChristmasDiscount()).thenReturn(0);
        when(eventDTO.getDateWeek()).thenReturn(WeekDiscountType.WEEKDAY.getDateType());
        when(eventDTO.getSpecialDiscount()).thenReturn(0);

        EventResult testResult = new EventResult(eventDTO, orderMenus);
        assertThat(testResult.getDiscountDetail().getTotalDiscount())
                .isEqualTo(0);
        assertThat(testResult.getGiftMenu())
                .isEqualTo(GiftMenu.NONE);
        assertThat(testResult.getRewardBadge())
                .isEqualTo(RewardBadge.NONE);
    }

    @ParameterizedTest
    @MethodSource("createNonDiscountOrderMenuUnderAmountData")
    @DisplayName("총액 10000원 이하 이벤트 미적용 결과 객체 생성")
    void createEventResultByNonDiscountFromAmount(Map<MenuItem, Integer> testMap) {
        orderMenus = testMap;

        setEventDTOFromAllEvent();

        EventResult testResult = new EventResult(eventDTO, orderMenus);
        assertThat(testResult.getDiscountDetail().getTotalDiscount())
                .isEqualTo(0);
        assertThat(testResult.getGiftMenu())
                .isEqualTo(GiftMenu.NONE);
        assertThat(testResult.getRewardBadge())
                .isEqualTo(RewardBadge.NONE);
    }

    @ParameterizedTest
    @MethodSource("createOrderMenusToTreeBadgeData")
    @DisplayName("이벤트 적용 트리 배지 부여 결과 객체 생성")
    void createEventResult(Map<MenuItem, Integer> testMap) {
        orderMenus = testMap;

        setEventDTOFromAllEvent();

        EventResult testResult = new EventResult(eventDTO, orderMenus);
        assertThat(testResult.getDiscountDetail().getTotalDiscount())
                .isBetween(10000, 19999);
        assertThat(testResult.getRewardBadge())
                .isEqualTo(RewardBadge.TREE);
    }

    private static Stream<Arguments> createNormalOrderMenusData() {
        return Stream.of(
                Arguments.of(
                        Map.of(MenuItem.APPETIZER_MUSHROOM_SOUP, 4, MenuItem.MAIN_SEAFOOD_PASTA, 4,
                                MenuItem.BEVERAGE_RED_WINE, 2)
                )
        );
    }

    private static Stream<Arguments> createNonDiscountOrderMenusData() {
        return Stream.of(
                Arguments.of(
                        Map.of(MenuItem.APPETIZER_MUSHROOM_SOUP, 4,
                                MenuItem.BEVERAGE_ZERO_COLA, 2)
                )
        );
    }

    private static Stream<Arguments> createNonDiscountOrderMenuUnderAmountData() {
        return Stream.of(
                Arguments.of(
                        Map.of(MenuItem.DESSERT_ICE_CREAM, 1)
                ),
                Arguments.of(
                        Map.of(MenuItem.APPETIZER_MUSHROOM_SOUP, 1)
                )
        );
    }

    private static Stream<Arguments> createOrderMenusToTreeBadgeData() {
        return Stream.of(
                Arguments.of(
                        Map.of(MenuItem.MAIN_CHRISTMAS_PASTA, 2, MenuItem.DESSERT_ICE_CREAM, 7)
                ),
                Arguments.of(
                        Map.of(MenuItem.MAIN_CHRISTMAS_PASTA, 2, MenuItem.DESSERT_CHOCO_CAKE, 3)
                )
        );
    }
}
