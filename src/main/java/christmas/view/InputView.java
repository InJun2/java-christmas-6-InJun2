package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String inputDate() {
        System.out.println(InputViewMessage.RESERVATION_DATE_INPUT.getMessage());
        return Console.readLine();
    }

    public String inputOrderMenus() {
        System.out.println(InputViewMessage.ORDER_ITEM_INPUT.getMessage());
        return Console.readLine();
    }
}
