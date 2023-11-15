package christmas.view;

import christmas.exception.PromotionException;
import christmas.model.event.dto.EventResultDTO;

public class OutputView {
    public void displayStartPromotion() {
        System.out.println(OutputViewMessage.START_OUTPUT_VIEW
                .getMessage());
    }

    public void displayPreviewEvent(int reservationDate) {
        System.out.println(OutputViewMessage.PREVIEW_EVENT
                .getMessage(reservationDate));
        System.out.println();
    }

    public void displayException(PromotionException e) {
        System.out.println(e.getMessage());
        System.out.println();
    }

    public void outputEventResult(EventResultDTO responseDto) {
        displayOrderMenu(responseDto.getOrderMenus());
        displayOriginalTotalAmount(responseDto.getTotalPriceBeforeDiscount());
        displayGiftMenu(responseDto.getGiftMenu());
        displayBenefitHistory(responseDto.getBenefitHistory());
        displayTotalBenefitAmount(responseDto.getTotalBenefit());
        displayExpectedPayment(responseDto.getTotalPriceAfterDiscount());
        displayDecemberEventBadge(responseDto.getRewardBadge());
    }

    private void displayOrderMenu(String orderMenus) {
        System.out.println(OutputViewMessage.ORDER_MENU
                .getMessage());
        System.out.println(orderMenus);
    }

    private void displayOriginalTotalAmount(String totalAmount) {
        System.out.println(OutputViewMessage.ORIGINAL_ORDER_TOTAL_AMOUNT
                .getMessage());
        displayOrder(totalAmount);
    }

    private void displayGiftMenu(String giftMenu) {
        System.out.println(OutputViewMessage.GIFT_MENU
                .getMessage());
        displayOrder(giftMenu);
    }

    private void displayBenefitHistory(String benefitHistory) {
        System.out.println(OutputViewMessage.BENEFIT_HISTORY
                .getMessage());
        System.out.println(benefitHistory);
    }

    private void displayTotalBenefitAmount(String benefitHistory) {
        System.out.println(OutputViewMessage.TOTAL_BENEFIT_AMOUNT
                .getMessage());
        displayOrder(benefitHistory);
    }

    private void displayExpectedPayment(String expectedPayment) {
        System.out.println(OutputViewMessage.EXPECTED_PAYMENT_AFTER_DISCOUNT
                .getMessage());
        displayOrder(expectedPayment);
    }

    private void displayDecemberEventBadge(String decemberEventBadge) {
        System.out.println(OutputViewMessage.DECEMBER_EVENT_BADGE
                .getMessage());
        System.out.println(decemberEventBadge);
    }

    private void displayOrder(String orderHistory) {
        System.out.println(orderHistory);
        System.out.println();
    }
}
