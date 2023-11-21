package christmas.model.event;

public enum GiftMenu {
    CHAMPAGNE("샴페인", 25000),
    NONE("없음", 0),
    ;

    private final String giftItem;
    private final int giftPrice;

    GiftMenu(String giftItem, int giftPrice) {
        this.giftItem = giftItem;
        this.giftPrice = giftPrice;
    }

    public String getGiftItem() {
        return giftItem;
    }

    public int getGiftPrice() {
        return giftPrice;
    }
}
