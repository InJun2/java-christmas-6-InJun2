package christmas.model.event.dto;

import christmas.model.event.EventResult;
import christmas.model.event.GiftMenu;

import java.text.NumberFormat;
import java.util.stream.Collectors;

public class EventResultDTO {
    private static final int GIFT_NUMBER = 1;
    private static final int DISCOUNT_AMOUNT_ZERO = 0;

    private final String orderMenus;
    private final String totalPriceBeforeDiscount;
    private final String giftMenu;
    private final String benefitHistory;
    private final String totalBenefit;
    private final String totalPriceAfterDiscount;
    private final String rewardBadge;

    public EventResultDTO(EventResult result) {
        this.orderMenus = initOrderMenus(result);
        this.totalPriceBeforeDiscount = initTotalPriceBeforeDiscount(result);
        this.giftMenu = initGiftMenu(result);
        this.benefitHistory = initBenefitHistory(result);
        this.totalBenefit = initTotalBenefit(result);
        this.totalPriceAfterDiscount = initPriceAfterDiscount(result);
        this.rewardBadge = result.getRewardBadge().getBadge();
    }

    private String initBenefitHistory(EventResult result) {
        StringBuilder builder = new StringBuilder();
        builder.append(displayChristmasBenefit(result));
        builder.append(displayWeekBenefit(result));
        builder.append(displaySpecialBenefit(result));
        builder.append(displayGiftEventBenefit(result));

        if (builder.isEmpty()) {
            return EventResultDTOText.NONE_BENEFIT.getText();
        }

        return builder.toString();
    }

    private String initTotalBenefit(EventResult eventResult) {
        int totalBenefit = eventResult.getDiscountDetail().getTotalDiscount() +
                eventResult.getGiftMenu().getGiftPrice();
        String formattedTotalBenefit = formatMoneyWithCommas(totalBenefit) +
                EventResultDTOText.MENU_PRICE_UNIT.getText();

        if(totalBenefit == DISCOUNT_AMOUNT_ZERO) {
            return formattedTotalBenefit;
        }

        return EventResultDTOText.DISCOUNT_PRICE.getText() + formattedTotalBenefit;
    }

    private String initPriceAfterDiscount(EventResult result) {
        int totalPrice = result.getDiscountDetail().getTotalPriceAfterDiscount();

        return formatMoneyWithCommas(totalPrice) + EventResultDTOText.MENU_PRICE_UNIT.getText();
    }

    private String displayChristmasBenefit(EventResult result) {
        if (result.getDiscountDetail().getChristmasDiscount() == DISCOUNT_AMOUNT_ZERO) {
            return EventResultDTOText.EMPTY_TEXT.getText();
        }

        return benefitHistoryToString(EventResultDTOText.CHRISTMAS_D_DAY_DISCOUNT.getText(),
                result.getDiscountDetail().getChristmasDiscount());
    }

    private String displaySpecialBenefit(EventResult result) {
        if (result.getDiscountDetail().getSpecialDiscount() == DISCOUNT_AMOUNT_ZERO) {
            return EventResultDTOText.EMPTY_TEXT.getText();
        }

        return benefitHistoryToString(EventResultDTOText.SPECIAL_DISCOUNT.getText(),
                result.getDiscountDetail().getSpecialDiscount());
    }

    private String displayWeekBenefit(EventResult result) {
        if (result.getDiscountDetail().getWeekTypeDiscount() == DISCOUNT_AMOUNT_ZERO) {
            return EventResultDTOText.EMPTY_TEXT.getText();
        }

        return benefitHistoryToString(result.getDiscountDetail().getWeekDiscountType().getDateType(),
                result.getDiscountDetail().getWeekTypeDiscount());
    }

    private String displayGiftEventBenefit(EventResult result) {
        if (result.getGiftMenu() == GiftMenu.NONE) {
            return EventResultDTOText.EMPTY_TEXT.getText();
        }

        return benefitHistoryToString(EventResultDTOText.GIFT_EVENT.getText(),
                result.getGiftMenu().getGiftPrice());
    }

    private String benefitHistoryToString(String discountEvent, int discountAmount) {
        return discountEvent +
                EventResultDTOText.BENEFIT_SEPARATOR.getText() +
                EventResultDTOText.DISCOUNT_PRICE.getText() +
                formatMoneyWithCommas(discountAmount) +
                EventResultDTOText.MENU_PRICE_UNIT.getText() +
                EventResultDTOText.NEW_LINE.getText();
    }

    private String initOrderMenus(EventResult eventResult) {
        return eventResult.getOrderMenus()
                .entrySet()
                .stream()
                .map(menu ->
                        "%s %d%s".formatted(menu.getKey().getItemName(), menu.getValue(),
                                EventResultDTOText.MENU_NUMBER.getText() + EventResultDTOText.NEW_LINE.getText()))
                .collect(Collectors.joining());
    }

    private String initTotalPriceBeforeDiscount(EventResult eventResult) {
        int totalPrice = eventResult.getDiscountDetail().getTotalPriceBeforeDiscount();

        return formatMoneyWithCommas(totalPrice);
    }

    private String initGiftMenu(EventResult eventResult) {
        if (eventResult.getGiftMenu().equals(GiftMenu.NONE)) {
            return GiftMenu.NONE.getGiftItem();
        }

        return GiftMenu.CHAMPAGNE.getGiftItem() + " " + GIFT_NUMBER + EventResultDTOText.MENU_NUMBER.getText();
    }

    private String formatMoneyWithCommas(int number) {
        return NumberFormat.getNumberInstance()
                .format(number);
    }

    public String getOrderMenus() {
        return orderMenus;
    }

    public String getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public String getGiftMenu() {
        return giftMenu;
    }

    public String getBenefitHistory() {
        return benefitHistory;
    }

    public String getTotalBenefit() {
        return totalBenefit;
    }

    public String getTotalPriceAfterDiscount() {
        return totalPriceAfterDiscount;
    }

    public String getRewardBadge() {
        return rewardBadge;
    }
}
