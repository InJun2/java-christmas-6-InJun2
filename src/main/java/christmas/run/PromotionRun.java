package christmas.run;

import christmas.controller.PromotionController;
import christmas.exception.PromotionException;
import christmas.model.event.dto.EventResultDTO;
import christmas.model.event.dto.ReservationDateEventDTO;
import christmas.model.menu.dto.OrderMenusDTO;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.function.Supplier;

public class PromotionRun {
    private final PromotionController controller = new PromotionController();
    private final InputView inputView;
    private final OutputView outputView;

    public PromotionRun(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void runPromotion() {
        ReservationDateEventDTO dateEventDto = inputCorrectReservationDate();
        OrderMenusDTO orderMenusDto = inputCorrectOrderMenus();
        displayEventPreview(dateEventDto);

        EventResultDTO responseDto = applyEventsAndGenerateResult(dateEventDto, orderMenusDto);
        outputEventResult(responseDto);
    }

    private ReservationDateEventDTO inputCorrectReservationDate() {
        outputView.displayStartPromotion();

        return getCorrectResult(this::generateDateEvent);
    }

    private OrderMenusDTO inputCorrectOrderMenus() {
        return getCorrectResult(this::receiveMenus);
    }

    private ReservationDateEventDTO generateDateEvent() {
        String inputDate = inputView.inputDate();

        return controller.generateEvent(inputDate);
    }

    private OrderMenusDTO receiveMenus() {
        String inputMenus = inputView.inputOrderMenus();

        return controller.receiveOrderMenus(inputMenus);
    }

    private void displayEventPreview(ReservationDateEventDTO dateEventDto) {
        outputView.displayPreviewEvent(dateEventDto.getDay());
    }

    private EventResultDTO applyEventsAndGenerateResult(ReservationDateEventDTO dateEventDTO,
                                                        OrderMenusDTO orderMenusDto) {
        return controller.applyEvents(dateEventDTO, orderMenusDto);
    }

    private void outputEventResult(EventResultDTO responseDto) {
        outputView.outputEventResult(responseDto);
    }

    private <T> T getCorrectResult(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (PromotionException e) {
                outputView.displayException(e);
            }
        }
    }
}
