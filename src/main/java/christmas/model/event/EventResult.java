package christmas.model.event;

import christmas.model.event.dto.ReservationDateEventDTO;
import christmas.model.menu.MenuItem;

import java.util.*;
import java.util.stream.Stream;

public class EventResult {
    private static final int MIN_EVENT_AMOUNT = 10_000;
    private static final int GIFT_AMOUNT = 120_000;

    private final Map<MenuItem, Integer> orderMenus;
    private final DiscountDetail discountDetail;
    private final GiftMenu giftMenu;
    private final RewardBadge rewardBadge;

    public EventResult(ReservationDateEventDTO dateEventDTO, Map<MenuItem, Integer> orderMenus) {
        this.orderMenus = orderMenus;
        this.discountDetail = initDiscountDetail(dateEventDTO);
        this.giftMenu = initGiftMenu();
        this.rewardBadge = initRewardBadge();
    }

    private DiscountDetail initDiscountDetail(ReservationDateEventDTO dateEventDTO) {
        int totalPriceBeforeDiscount = getTotalPriceBeforeDiscount();
        if (totalPriceBeforeDiscount < MIN_EVENT_AMOUNT) {
            return new DiscountDetail(dateEventDTO, totalPriceBeforeDiscount);
        }
        int weekDiscount = calculateWeekDiscount(dateEventDTO);

        return new DiscountDetail(dateEventDTO, totalPriceBeforeDiscount, weekDiscount);
    }

    private GiftMenu initGiftMenu() {
        if (discountDetail.getTotalPriceBeforeDiscount() < GIFT_AMOUNT) {
            return GiftMenu.NONE;
        }

        return GiftMenu.CHAMPAGNE;
    }

    private RewardBadge initRewardBadge() {
        int benefit = discountDetail.getTotalDiscount() + giftMenu.getGiftPrice();

        return Arrays.stream(RewardBadge.values())
                .filter(badge ->
                        benefit >= badge.getBenefit())
                .max(Comparator.comparingInt(RewardBadge::getBenefit))
                .orElse(RewardBadge.NONE);
    }

    private int getTotalPriceBeforeDiscount() {
        return generateOrderMenuEntry(orderMenus)
                .mapToInt(menu ->
                        menu.getKey().getItemPrice() * menu.getValue())
                .sum();
    }

    private int calculateWeekDiscount(ReservationDateEventDTO dateEventDTO) {
        WeekDiscountType type = WeekDiscountType.checkWeekType(dateEventDTO.getDateWeek());

        return generateOrderMenuEntry(orderMenus)
                .filter(menu ->
                        menu.getKey().getMenuCategory() == type.getDiscountCategory())
                .mapToInt(menu ->
                        menu.getValue() * type.getDiscountPrice())
                .sum();
    }

    private <K, V> Stream<Map.Entry<K, V>> generateOrderMenuEntry(Map<K, V> orderMenus) {
        return orderMenus.entrySet()
                .stream();
    }

    public Map<MenuItem, Integer> getOrderMenus() {
        return Collections.unmodifiableMap(orderMenus);
    }

    public DiscountDetail getDiscountDetail() {
        return discountDetail;
    }

    public GiftMenu getGiftMenu() {
        return giftMenu;
    }

    public RewardBadge getRewardBadge() {
        return rewardBadge;
    }
}
