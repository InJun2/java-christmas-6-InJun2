package christmas.converter;

import christmas.model.event.ReservationDateEvent;
import christmas.model.event.dto.ReservationDateEventDto;
import christmas.model.menu.OrderMenus;
import christmas.model.menu.dto.OrderMenusDto;

public class PromotionConverter {
    public ReservationDateEventDto toDto(ReservationDateEvent event) {
        return new ReservationDateEventDto(event);
    }

    public OrderMenusDto toDto(OrderMenus orderMenus) {
        return new OrderMenusDto(orderMenus);
    }
}
