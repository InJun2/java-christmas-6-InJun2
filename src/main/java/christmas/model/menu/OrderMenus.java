package christmas.model.menu;

import java.util.EnumMap;
import java.util.Map;

public class OrderMenus {
    private final Map<MenuItem, Integer> orderMenus;

    public OrderMenus(Map<String, Integer> orderMenus) {
        this.orderMenus = new EnumMap<>(MenuItem.class);
    }

    public Map<MenuItem, Integer> getOrderMenus() {
        return new EnumMap<>(orderMenus);
    }
}
