package christmas.exception;

public class PromotionException extends IllegalArgumentException {
    public PromotionException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getErrorMessage());
    }
}
