package christmas.service;

import christmas.model.event.*;
import christmas.model.menu.MenuItem;
import christmas.util.EventResultTextFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EventResultTextFactoryTest {
    private EventResultTextFactory factory;
    private EventResult result;
    private DiscountDetail detail;

    @BeforeEach
    void setUp() {
        result = mock(EventResult.class);
        detail = mock(DiscountDetail.class);
        when(result.getDiscountDetail()).thenReturn(detail);
        factory = new EventResultTextFactory(result);
    }

    @Test
    @DisplayName("EventResultDTO 총 혜택 문자열 정상 생성")
    void createBenefitHistoryForAllEventApply() {
        allEventApply();

        assertThat(factory.initBenefitHistory())
                .contains("크리스마스 디데이 할인", "평일 할인", "특별 할인", "증정 이벤트", "2,000원");
    }

    @Test
    @DisplayName("EventResultDTO 총 할인 금액 문자열 생성")
    void createTotalBenefitForAllEventApply() {
        allEventApply();

        assertThat(factory.initTotalBenefit())
                .contains("-31,000원");
    }

    private void allEventApply() {
        when(detail.getChristmasDiscount()).thenReturn(2000);
        when(detail.getWeekTypeDiscount()).thenReturn(2000);
        when(detail.getSpecialDiscount()).thenReturn(2000);
        when(detail.getTotalDiscount()).thenReturn(6000);
        when(detail.getWeekDiscountType()).thenReturn(WeekDiscountType.WEEKDAY);
        when(result.getGiftMenu()).thenReturn(GiftMenu.CHAMPAGNE);
    }

    @Test
    @DisplayName("EventResultDTO 총 혜택 문자열 정상 생성")
    void createBenefitHistoryForNonEventApply() {
        noneEventApply();

        assertThat(factory.initBenefitHistory())
                .contains("없음");
    }

    private void noneEventApply() {
        when(detail.getChristmasDiscount()).thenReturn(0);
        when(detail.getWeekTypeDiscount()).thenReturn(0);
        when(detail.getSpecialDiscount()).thenReturn(0);
        when(detail.getWeekDiscountType()).thenReturn(WeekDiscountType.WEEKDAY);
        when(result.getGiftMenu()).thenReturn(GiftMenu.NONE);
    }

    @Test
    @DisplayName("EventResultDTO 주문 메뉴 문자열 정상 생성")
    void createAllOrderMenus() {
        Map<MenuItem, Integer> orderMenus = createOrderMenus();
        when(result.getOrderMenus()).thenReturn(orderMenus);

        assertThat(factory.initOrderMenus())
                .contains("타파스", "해산물파스타", "2개", "3개");
    }

    private Map<MenuItem, Integer> createOrderMenus() {
        Map<MenuItem, Integer> testMap = new HashMap<>();
        testMap.put(MenuItem.APPETIZER_TAPAS, 2);
        testMap.put(MenuItem.MAIN_SEAFOOD_PASTA, 3);
        return testMap;
    }

    @Test
    @DisplayName("EventResultDTO 샴페인 증정 문자열 생성")
    void createGiftMenu() {
        when(result.getGiftMenu()).thenReturn(GiftMenu.CHAMPAGNE);

        assertThat(factory.initGiftMenu())
                .contains("샴페인", "1개");
    }

    @Test
    @DisplayName("EventResultDTO 이벤트 배지 부여 문자열 생성")
    void createRewardBadge() {
        when(result.getRewardBadge()).thenReturn(RewardBadge.SANTA);

        assertThat(factory.initRewardBadge())
                .contains("산타");
    }
}
