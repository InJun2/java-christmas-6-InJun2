package christmas;


import camp.nextstep.edu.missionutils.Console;
import christmas.run.PromotionRun;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        try {
            InputView inputView = SingletonView.getInputView();
            OutputView outputView = SingletonView.getOutputView();
            PromotionRun promotionRun = new PromotionRun(inputView, outputView);
            promotionRun.runPromotion();
        } finally {
            Console.close();
        }
    }
}
