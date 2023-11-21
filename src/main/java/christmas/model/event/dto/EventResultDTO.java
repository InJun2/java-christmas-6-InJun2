package christmas.model.event.dto;

public class EventResultDTO {
    private final String orderMenus;
    private final String totalPriceBeforeDiscount;
    private final String giftMenu;
    private final String benefitHistory;
    private final String totalBenefit;
    private final String totalPriceAfterDiscount;
    private final String rewardBadge;

    private EventResultDTO(Builder builder) {
        this.orderMenus = builder.orderMenus;
        this.totalPriceBeforeDiscount = builder.totalPriceBeforeDiscount;
        this.giftMenu = builder.giftMenu;
        this.benefitHistory = builder.benefitHistory;
        this.totalBenefit = builder.totalBenefit;
        this.totalPriceAfterDiscount = builder.totalPriceAfterDiscount;
        this.rewardBadge = builder.rewardBadge;
    }

    public static class Builder {
        private String orderMenus;
        private String totalPriceBeforeDiscount;
        private String giftMenu;
        private String benefitHistory;
        private String totalBenefit;
        private String totalPriceAfterDiscount;
        private String rewardBadge;

        public Builder() {
        }

        public Builder orderMenus(String orderMenus) {
            this.orderMenus = orderMenus;
            return this;
        }

        public Builder totalPriceBeforeDiscount(String totalPriceBeforeDiscount) {
            this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
            return this;
        }

        public Builder giftMenu(String giftMenu) {
            this.giftMenu = giftMenu;
            return this;
        }

        public Builder benefitHistory(String benefitHistory) {
            this.benefitHistory = benefitHistory;
            return this;
        }

        public Builder totalBenefit(String totalBenefit) {
            this.totalBenefit = totalBenefit;
            return this;
        }

        public Builder totalPriceAfterDiscount(String totalPriceAfterDiscount) {
            this.totalPriceAfterDiscount = totalPriceAfterDiscount;
            return this;
        }

        public Builder rewardBadge(String rewardBadge) {
            this.rewardBadge = rewardBadge;
            return this;
        }

        public EventResultDTO build() {
            return new EventResultDTO(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getOrderMenus() {
        return orderMenus;
    }

    public String getTotalPriceBeforeDiscount() {
        return totalPriceBeforeDiscount;
    }

    public String getGiftMenu() {
        return giftMenu;
    }

    public String getBenefitHistory() {
        return benefitHistory;
    }

    public String getTotalBenefit() {
        return totalBenefit;
    }

    public String getTotalPriceAfterDiscount() {
        return totalPriceAfterDiscount;
    }

    public String getRewardBadge() {
        return rewardBadge;
    }
}
