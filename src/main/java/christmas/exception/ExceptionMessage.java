package christmas.exception;

public enum ExceptionMessage {

    INVALID_INPUT_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_INPUT_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_INTEGER_FORMAT("입력된 문자가 정수로 변환되지 않습니다."),
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
