package christmas.controller;

import christmas.model.event.dto.EventResultDTO;
import christmas.model.event.dto.ReservationDateEventDTO;
import christmas.model.menu.dto.OrderMenusDTO;
import christmas.service.PromotionService;

public class PromotionController {
    private final PromotionService service = new PromotionService();

    public ReservationDateEventDTO generateEvent(String inputDate) {
        return service.generateEvent(inputDate);
    }

    public OrderMenusDTO receiveOrderMenus(String inputMenus) {
        return service.receiveOrderMenus(inputMenus);
    }

    public EventResultDTO applyEvents(ReservationDateEventDTO dateEventDTO, OrderMenusDTO orderMenusDto) {
        return service.applyEvents(dateEventDTO, orderMenusDto);
    }
}
