package christmas.util;

import christmas.model.event.EventResult;
import christmas.model.event.EventResultText;
import christmas.model.event.GiftMenu;

import java.text.NumberFormat;
import java.util.stream.Collectors;

public class EventResultDTOFactory {
    private static final int GIFT_NUMBER = 1;
    private static final int DISCOUNT_AMOUNT_ZERO = 0;
    private static final String ORDER_OUTPUT_REGEX = "%s %d%s";

    private final EventResult result;

    public EventResultDTOFactory(EventResult result) {
        this.result = result;
    }

    public String initBenefitHistory() {
        StringBuilder builder = new StringBuilder();
        builder.append(displayChristmasBenefit());
        builder.append(displayWeekBenefit());
        builder.append(displaySpecialBenefit());
        builder.append(displayGiftEventBenefit());

        if (builder.isEmpty()) {
            return EventResultText.NONE_BENEFIT.getText();
        }

        return builder.toString();
    }

    public String initTotalBenefit() {
        int totalBenefit = result.getDiscountDetail().getTotalDiscount() +
                result.getGiftMenu().getGiftPrice();
        String formattedTotalBenefit = formatMoneyWithCommas(totalBenefit) +
                EventResultText.MENU_PRICE_UNIT.getText();

        if(totalBenefit == DISCOUNT_AMOUNT_ZERO) {
            return formattedTotalBenefit;
        }

        return EventResultText.DISCOUNT_PRICE.getText() + formattedTotalBenefit;
    }

    public String initPriceAfterDiscount() {
        int totalPrice = result.getDiscountDetail().getTotalPriceAfterDiscount();

        return formatMoneyWithCommas(totalPrice) + EventResultText.MENU_PRICE_UNIT.getText();
    }

    public String initOrderMenus() {
        return result.getOrderMenus()
                .entrySet()
                .stream()
                .map(menu ->
                        ORDER_OUTPUT_REGEX.formatted(menu.getKey().getItemName(), menu.getValue(),
                                EventResultText.MENU_NUMBER.getText() + EventResultText.NEW_LINE.getText()))
                .collect(Collectors.joining());
    }

    public String initTotalPriceBeforeDiscount() {
        int totalPrice = result.getDiscountDetail().getTotalPriceBeforeDiscount();

        return formatMoneyWithCommas(totalPrice);
    }

    public String initGiftMenu() {
        if (result.getGiftMenu().equals(GiftMenu.NONE)) {
            return GiftMenu.NONE.getGiftItem();
        }

        return GiftMenu.CHAMPAGNE.getGiftItem() + " " + GIFT_NUMBER + EventResultText.MENU_NUMBER.getText();
    }

    public String initRewardBadge() {
        return result.getRewardBadge().getBadge();
    }

    private String displayChristmasBenefit() {
        if (result.getDiscountDetail().getChristmasDiscount() == DISCOUNT_AMOUNT_ZERO) {
            return EventResultText.EMPTY_TEXT.getText();
        }

        return benefitHistoryToString(EventResultText.CHRISTMAS_D_DAY_DISCOUNT.getText(),
                result.getDiscountDetail().getChristmasDiscount());
    }

    private String displaySpecialBenefit() {
        if (result.getDiscountDetail().getSpecialDiscount() == DISCOUNT_AMOUNT_ZERO) {
            return EventResultText.EMPTY_TEXT.getText();
        }

        return benefitHistoryToString(EventResultText.SPECIAL_DISCOUNT.getText(),
                result.getDiscountDetail().getSpecialDiscount());
    }

    private String displayWeekBenefit() {
        if (result.getDiscountDetail().getWeekTypeDiscount() == DISCOUNT_AMOUNT_ZERO) {
            return EventResultText.EMPTY_TEXT.getText();
        }

        return benefitHistoryToString(result.getDiscountDetail().getWeekDiscountType().getDateType(),
                result.getDiscountDetail().getWeekTypeDiscount());
    }

    private String displayGiftEventBenefit() {
        if (result.getGiftMenu() == GiftMenu.NONE) {
            return EventResultText.EMPTY_TEXT.getText();
        }

        return benefitHistoryToString(EventResultText.GIFT_EVENT.getText(),
                result.getGiftMenu().getGiftPrice());
    }

    private String benefitHistoryToString(String discountEvent, int discountAmount) {
        return discountEvent +
                EventResultText.BENEFIT_SEPARATOR.getText() +
                EventResultText.DISCOUNT_PRICE.getText() +
                formatMoneyWithCommas(discountAmount) +
                EventResultText.MENU_PRICE_UNIT.getText() +
                EventResultText.NEW_LINE.getText();
    }

    private String formatMoneyWithCommas(int number) {
        return NumberFormat.getNumberInstance()
                .format(number);
    }
}
