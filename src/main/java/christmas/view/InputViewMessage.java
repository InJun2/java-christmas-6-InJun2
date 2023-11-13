package christmas.view;

enum InputViewMessage {
    RESERVATION_DATE_INPUT("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ORDER_ITEM_INPUT("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    ;

    private final String message;


    InputViewMessage(String message) {
        this.message = message;
    }

    protected String getMessage() {
        return message;
    }
}
