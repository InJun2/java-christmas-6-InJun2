package christmas;

import christmas.view.InputView;
import christmas.view.OutputView;

class SingletonView {
    private static InputView inputView;
    private static OutputView outputView;

    private SingletonView() {}

    public static InputView getInputView() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    public static OutputView getOutputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }
}
