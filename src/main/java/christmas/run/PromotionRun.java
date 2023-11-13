package christmas.run;

import christmas.controller.PromotionController;
import christmas.model.event.ReservationDateEvent;
import christmas.model.event.dto.ReservationDateEventDto;
import christmas.model.menu.dto.OrderMenusDto;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PromotionRun {
    private final PromotionController controller = new PromotionController();
    private final InputView inputView;
    private final OutputView outputView;

    public PromotionRun(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ReservationDateEventDto dateEventDto = generateEventByDate();

        OrderMenusDto orderMenusDto = receiveOrderMenus();

//        -> Dto 이벤트 비교
    }

    private ReservationDateEventDto generateEventByDate() {
        String inputDate = inputReservationDate();
        return controller.generateEvent(inputDate);
    }

    private OrderMenusDto receiveOrderMenus() {
        String inputMenus = inputOrderMenus();
        return controller.receiveOrderMenus(inputMenus);
    }

    private String inputReservationDate() {
        outputView.displayStartPromotion();
        return inputView.inputDate();
    }

    private String inputOrderMenus() {
        return inputView.inputOrderMenus();
    }

}
