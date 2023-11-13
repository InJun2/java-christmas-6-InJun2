package christmas.model.event;

public enum DiscountDateType {
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인");

    private final String description;

    DiscountDateType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
