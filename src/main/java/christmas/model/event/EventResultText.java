package christmas.model.event;

public enum EventResultText {
    EMPTY_TEXT(""),
    SPACE(" "),
    MENU_NUMBER("개"),
    MENU_PRICE_UNIT ("원"),
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인"),
    SPECIAL_DISCOUNT("특별 할인"),
    GIFT_EVENT ("증정 이벤트"),
    NONE_BENEFIT ("없음"),
    DISCOUNT_PRICE ("-"),
    BENEFIT_SEPARATOR(": "),
    NEW_LINE("\n"),
    ;

    private final String text;

    EventResultText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
