package christmas.service;

import christmas.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TextProcessorTest {
    private final TextProcessor textProcessor = new TextProcessor();

    @ParameterizedTest
    @ValueSource(strings = {"3", "21", "33", "75"})
    @DisplayName("예약 일자 문자열 정수로 정상 변환")
    void parseToIntegerDay(String inputString) {
        assertThat(textProcessor.parseInputDay(inputString))
                .isEqualTo(Integer.parseInt(inputString));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "2 1", "test"})
    @DisplayName("예약 일자 문자열 정수로 변환 예외")
    void invalidParseToIntegerDay(String inputString) {
        assertThatThrownBy(() -> textProcessor.parseInputDay(inputString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_INPUT_DATE.getErrorMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"과자-1,음료-2", "양송이수프-3, 초코케이크-2", "피자-1,치킨-1,탄산-2"})
    @DisplayName("주문 메뉴 문자열 정상 분리")
    void createProcessOrderMap(String inputString) {
        Map<String, Integer> testMap = textProcessor.processOrder(inputString);

        String[] splitInput = inputString.split(",");
        String result = splitInput[0].split("-")[0];

        assertThat(testMap)
                .isInstanceOf(Map.class);
        assertThat(testMap.entrySet())
                .anyMatch(map -> map.getKey().equals(result));
    }

    @ParameterizedTest
    @ValueSource(strings = {"testInput", "양송이수프--3", "3-양송이수프, 양송이수프-3"})
    @DisplayName("주문 메뉴 문자열 분리시 입력 포맷 불일치 예외")
    void invalidProcessOrderByInputFormat(String inputString) {
        assertThatThrownBy(() -> textProcessor.processOrder(inputString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_INPUT_ORDER.getErrorMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"수프-3, 수프-1", "케이크-1, 케이크-2, 음료-1", "파스타-1,파스타-2,음료-1"})
    @DisplayName("주문 메뉴 문자열 분리시 입력 중복 예외")
    void invalidProcessOrderByDuplication(String inputString) {
        assertThatThrownBy(() -> textProcessor.processOrder(inputString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_INPUT_ORDER.getErrorMessage());
    }
}
