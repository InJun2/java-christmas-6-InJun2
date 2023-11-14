package christmas.run;

import christmas.controller.PromotionController;
import christmas.model.event.dto.EventResultDTO;
import christmas.model.event.dto.ReservationDateEventDTO;
import christmas.model.menu.dto.OrderMenusDTO;
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
        ReservationDateEventDTO dateEventDto = generateEventByDate();
        OrderMenusDTO orderMenusDto = receiveOrderMenus();

        EventResultDTO responseDto = applyEventsAndGenerateResult(dateEventDto, orderMenusDto);
        System.out.println(responseDto.getBenefitHistory());
        System.out.println(responseDto.getOrderMenus());
        System.out.println(responseDto.getGiftMenu());
        System.out.println(responseDto.getTotalBenefit());
        System.out.println(responseDto.getTotalPriceBeforeDiscount());
        System.out.println(responseDto.getTotalPriceAfterDiscount());
        System.out.println(responseDto.getRewardBadge());
    }

    private ReservationDateEventDTO generateEventByDate() {
        String inputDate = inputReservationDate();
        return controller.generateEvent(inputDate);
    }

    private OrderMenusDTO receiveOrderMenus() {
        String inputMenus = inputOrderMenus();
        return controller.receiveOrderMenus(inputMenus);
    }

    private EventResultDTO applyEventsAndGenerateResult(ReservationDateEventDTO dateEventDTO,
                                                        OrderMenusDTO orderMenusDto) {
        return controller.applyEvents(dateEventDTO, orderMenusDto);
    }

    private String inputReservationDate() {
        outputView.displayStartPromotion();
        return inputView.inputDate();
    }

    private String inputOrderMenus() {
        return inputView.inputOrderMenus();
    }
}
