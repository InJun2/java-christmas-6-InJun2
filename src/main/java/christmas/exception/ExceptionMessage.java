package christmas.exception;

public enum ExceptionMessage {
    INVALID_INPUT_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_INPUT_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_MENU_ITEM("해당 메뉴가 존재하지 않습니다."),
    INVALID_WEEK_DISCOUNT_TYPE("잘못된 주간 할인 타입입니다."),
    ;

    private static final String PREFIX = "[ERROR] ";
    private final String errorMessage;

    ExceptionMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return PREFIX + errorMessage;
    }
}
