package christmas.converter;

import christmas.model.event.EventResult;
import christmas.model.event.ReservationDateEvent;
import christmas.model.event.dto.EventResultDTO;
import christmas.model.event.dto.ReservationDateEventDTO;
import christmas.model.menu.MenuItem;
import christmas.model.menu.OrderMenus;
import christmas.model.menu.dto.OrderMenusDTO;

import java.util.Map;
import java.util.stream.Collectors;

public class PromotionConverter {
    public ReservationDateEventDTO toDto(ReservationDateEvent event) {
        return new ReservationDateEventDTO(event);
    }

    public OrderMenusDTO toDto(OrderMenus orderMenus) {
        return new OrderMenusDTO(orderMenus);
    }

    public EventResultDTO toDto(EventResult eventResult) {
        return new EventResultDTO(eventResult);
    }

    public Map<MenuItem, Integer> convertToMenuItem(OrderMenusDTO orderMenusDTO) {
        return orderMenusDTO.getOrderMenus()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> MenuItem.findMenuItemByName(entry.getKey()),
                        Map.Entry::getValue
                ));
    }
}
