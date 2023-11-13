package christmas.controller;

import christmas.model.event.dto.ReservationDateEventDto;
import christmas.model.menu.dto.OrderMenusDto;
import christmas.service.PromotionService;

public class PromotionController {
    private final PromotionService service = new PromotionService();

    public ReservationDateEventDto generateEvent(String inputDate) {
        return service.generateEvent(inputDate);
    }

    public OrderMenusDto receiveOrderMenus(String inputMenus) {
        return service.receiveOrderMenus(inputMenus);
    }
}
