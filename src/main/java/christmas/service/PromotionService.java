package christmas.service;

import christmas.util.EventResultTextFactory;
import christmas.util.PromotionConverter;
import christmas.model.event.EventResult;
import christmas.model.event.ReservationDateEvent;
import christmas.model.event.dto.EventResultDTO;
import christmas.model.event.dto.ReservationDateEventDTO;
import christmas.model.menu.OrderMenus;
import christmas.model.menu.dto.OrderMenusDTO;

import java.util.Map;

public class PromotionService {
    private final TextProcessor textProcessor = new TextProcessor();
    private final PromotionConverter converter = new PromotionConverter();

    public ReservationDateEventDTO generateEvent(String inputDay) {
        int day = textProcessor.parseInputDay(inputDay);
        ReservationDateEvent event = new ReservationDateEvent(day);

        return converter.toDto(event);
    }

    public OrderMenusDTO receiveOrderMenus(String inputMenus) {
        Map<String, Integer> inputOrderMenus = textProcessor.processOrder(inputMenus);
        OrderMenus orderMenus = new OrderMenus(inputOrderMenus);

        return converter.toDto(orderMenus);
    }

    public EventResultDTO applyEvents(ReservationDateEventDTO dateEventDTO, OrderMenusDTO orderMenusDto) {
        EventResult eventResult = new EventResult(dateEventDTO
                , converter.convertToMenuItem(orderMenusDto));

        return converter.toDto(new EventResultTextFactory(eventResult));
    }
}
