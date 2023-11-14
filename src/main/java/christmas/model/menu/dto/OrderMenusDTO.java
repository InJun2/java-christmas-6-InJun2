package christmas.model.menu.dto;

import christmas.model.menu.OrderMenus;

import java.util.Map;

public class OrderMenusDTO {
    private final Map<String, Integer> orderMenus;

    public OrderMenusDTO(OrderMenus orderMenus) {
        this.orderMenus = orderMenus.getOrderMenus();
    }

    public Map<String, Integer> getOrderMenus() {
        return orderMenus;
    }
}
