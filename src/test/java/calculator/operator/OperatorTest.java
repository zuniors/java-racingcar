package calculator.operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("연산자 enum 테스트")
class OperatorTest {

    @MethodSource
    @ParameterizedTest(name = "{0} -> {1}")
    @DisplayName("문자열을 받았을때 해당 연산자를 잘 리턴하는지")
    void of(final String symbol, final Operator expectedOperator) {
        assertThat(Operator.of(symbol)).isEqualByComparingTo(expectedOperator);
    }

    private static Stream<Arguments> of() {
        return Stream.of(
                Arguments.of("+", Operator.PLUS),
                Arguments.of("-", Operator.MINUS),
                Arguments.of("*", Operator.MULTIPLY),
                Arguments.of("/", Operator.DIVIDE)
        );
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("null 이거나 빈 문자열 혹은 포함하지 않은 심볼을 받았을 때 예외를 발생시키는지")
    void ofException(final String symbol) {
        assertThatThrownBy(() -> Operator.of(symbol))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<String> ofException() {
        return Stream.of(
                null,
                "",
                "$",
                "%"
        );
    }

    @MethodSource
    @ParameterizedTest(name = "{1} {0} {2} = {3}")
    @DisplayName("연산자 enum이 해당 기호에 맞는 연산을 잘 수행하는지")
    void operate(final Operator operator, final int x, final int y, final int expected) {
        assertThat(operator.operate(x, y)).isEqualTo(expected);
    }

    private static Stream<Arguments> operate() {
        return Stream.of(
                Arguments.of(Operator.PLUS, 10, 5, 15),
                Arguments.of(Operator.MINUS, 10, 5, 5),
                Arguments.of(Operator.MULTIPLY, 10, 5, 50),
                Arguments.of(Operator.DIVIDE, 10, 5, 2)
        );
    }
}