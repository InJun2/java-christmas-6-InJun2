package christmas.model.service;

import christmas.model.event.ReservationDateEvent;

public class PromotionService {
    private final TextProcessor textProcessor;

    public PromotionService() {
        this.textProcessor = new TextProcessor();
    }

    public ReservationDateEvent generateEvent(String inputDay) {
        int day = textProcessor.parseInputDay(inputDay);
        ReservationDateEvent event = new ReservationDateEvent(day);
        return event;
    }
}
