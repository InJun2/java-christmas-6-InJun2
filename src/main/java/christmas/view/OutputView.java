package christmas.view;

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

    public void displayOrderMenu(String orderMenus) {
        System.out.println(OutputViewMessage.ORDER_MENU
                .getMessage());
        displayOrderHistory(orderMenus);
    }

    public void displayOriginalTotalAmount(String totalAmount) {
        System.out.println(OutputViewMessage.ORIGINAL_ORDER_TOTAL_AMOUNT
                .getMessage());
        displayOrderHistory(totalAmount);
    }

    public void displayGiftMenu(String giftMenu) {
        System.out.println(OutputViewMessage.GIFT_MENU
                .getMessage());
        displayOrderHistory(giftMenu);
    }

    public void displayBenefitHistory(String benefitHistory) {
        System.out.println(OutputViewMessage.BENEFIT_HISTORY
                .getMessage());
        displayOrderHistory(benefitHistory);
    }
    public void displayTotalBenefitAmount(String benefitHistory) {
        System.out.println(OutputViewMessage.TOTAL_BENEFIT_AMOUNT
                .getMessage());
        displayOrderHistory(benefitHistory);
    }

    public void displayExpectedPayment(String expectedPayment) {
        System.out.println(OutputViewMessage.EXPECTED_PAYMENT_AFTER_DISCOUNT
                .getMessage());
        displayOrderHistory(expectedPayment);
    }

    public void displayDecemberEventBadge(String decemberEventBadge) {
        System.out.println(OutputViewMessage.DECEMBER_EVENT_BADGE
                .getMessage());
        displayOrderHistory(decemberEventBadge);
    }

    private void displayOrderHistory(String orderHistory) {
        System.out.println(orderHistory);
        System.out.println();
    }
}
