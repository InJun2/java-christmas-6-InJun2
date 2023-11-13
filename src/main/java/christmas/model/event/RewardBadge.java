package christmas.model.event;

public enum RewardBadge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000),
    NONE("없음", 0),
    ;

    private final String badge;
    private final int benefit;

    RewardBadge(String badge, int benefit) {
        this.badge = badge;
        this.benefit = benefit;
    }

    public String getBadge() {
        return badge;
    }

    public int getBenefit() {
        return benefit;
    }
}
