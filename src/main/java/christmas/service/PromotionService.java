package christmas.service;

import christmas.converter.PromotionConverter;
import christmas.model.event.ReservationDateEvent;
import christmas.model.event.dto.ReservationDateEventDto;
import christmas.model.menu.OrderMenus;
import christmas.model.menu.dto.OrderMenusDto;

import java.util.Map;

public class PromotionService {
    private final TextProcessor textProcessor = new TextProcessor();
    private final PromotionConverter converter = new PromotionConverter();

    public ReservationDateEventDto generateEvent(String inputDay) {
        int day = textProcessor.parseInputDay(inputDay);
        ReservationDateEvent event = new ReservationDateEvent(day);

        return converter.toDto(event);
    }


    public OrderMenusDto receiveOrderMenus(String inputMenus) {
        Map<String, Integer> inputOrderMenus = textProcessor.processOrder(inputMenus);
        OrderMenus orderMenus = new OrderMenus(inputOrderMenus);

        return converter.toDto(orderMenus);
    }
}
