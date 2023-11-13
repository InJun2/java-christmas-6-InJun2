package christmas.run;

import christmas.controller.PromotionController;
import christmas.model.event.ReservationDateEvent;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PromotionRun {
    private final InputView inputView;
    private final OutputView outputView;
    private final PromotionController controller;

    public PromotionRun(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        controller = new PromotionController();
    }

    public void run() {
        generateEventByDate();
    }

    private ReservationDateEvent generateEventByDate() {
        String inputDate = inputReservationDate();
        return controller.generateEvent(inputDate);
    }

    private String inputReservationDate() {
        outputView.displayStartPromotion();
        return inputView.inputDate();
    }

}
