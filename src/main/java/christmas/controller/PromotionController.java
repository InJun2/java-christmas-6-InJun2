package christmas.controller;

import christmas.model.event.ReservationDateEvent;
import christmas.model.service.PromotionService;

public class PromotionController {
    private final PromotionService service;

    public PromotionController() {
        service = new PromotionService();
    }

    public ReservationDateEvent generateEvent(String inputDate) {
        return service.generateEvent(inputDate);
    }
}
