package christmas.model;

import christmas.exception.ExceptionMessage;
import christmas.model.date.ReservationDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ReservationDateTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 7, 24, 31})
    @DisplayName("예약 일자 객체 테스트")
    void createReservationDate(int day) {
        assertThat(new ReservationDate(day))
                .isInstanceOf(ReservationDate.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 32})
    @DisplayName("예약 일자 예외 객체 테스트")
    void createReservationDateByInvalidDay(int day) {
        assertThatThrownBy(() -> new ReservationDate(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_INPUT_DATE.getErrorMessage());
    }
}
