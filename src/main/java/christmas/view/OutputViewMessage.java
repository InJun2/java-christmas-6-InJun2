package christmas.view;

enum OutputViewMessage {
    START_OUTPUT_VIEW("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    PREVIEW_EVENT("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("<주문 메뉴>"),
    ORIGINAL_ORDER_TOTAL_AMOUNT("<할인 전 총주문 금액>"),
    GIFT_MENU("<증정 메뉴>"),
    BENEFIT_HISTORY("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT("<총혜택 금액>"),
    EXPECTED_PAYMENT_AFTER_DISCOUNT("<할인 후 예상 결제 금액>"),
    DECEMBER_EVENT_BADGE("<12월 이벤트 배지>"),
    ;

    private final String message;

    OutputViewMessage(String message) {
        this.message = message;
    }

    protected String getMessage() {
        return message;
    }

    protected String getMessage(int reservationDate) {
        return String.format(message, reservationDate);
    }
}
