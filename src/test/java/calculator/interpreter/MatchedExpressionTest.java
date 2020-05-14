package calculator.interpreter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("식이 올바르게 매치되는지 테스트")
class MatchedExpressionTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("올바른 식이 들어왔을때")
    void constructor(final String exp) {
        assertThatCode(() -> new MatchedExpression(exp))
                .doesNotThrowAnyException();
    }

    private static Stream<String> constructor() {
        return Stream.of(
                "+2",
                "-2",
                "1+2",
                "1+2-3*4/1"
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("null 혹은 빈 문자열이 들어왔을 경우 예외를 던진다")
    void constructorException(final String exp) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new MatchedExpression(exp));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("올바른 산술식인지 확인")
    void isNumber(final MatchedExpression matchedExpression, final boolean isNumber) {
        assertThat(matchedExpression.isValidExpression()).isEqualTo(isNumber);
    }

    private static Stream<Arguments> isNumber() {
        return Stream.of(
                Arguments.of(new MatchedExpression("2"), false),
                Arguments.of(new MatchedExpression("+2"), false),
                Arguments.of(new MatchedExpression("-2"), false),
                Arguments.of(new MatchedExpression("1+2"), true),
                Arguments.of(new MatchedExpression("-2+2"), true),
                Arguments.of(new MatchedExpression("1+2-3*4/1"), true)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("식이 제대로 분리되는지 테스트")
    void extract(final MatchedExpression matchedExpression,
                 final String subExp,
                 final String operator,
                 final String operand) {
        assertThat(matchedExpression.getSubExpressionString()).isEqualTo(subExp);
        assertThat(matchedExpression.getOperatorString()).isEqualTo(operator);
        assertThat(matchedExpression.getOperandString()).isEqualTo(operand);
    }

    private static Stream<Arguments> extract() {
        return Stream.of(
                Arguments.of(new MatchedExpression("1+2"), "1", "+", "2"),
                Arguments.of(new MatchedExpression("-2+2"), "-2", "+", "2"),
                Arguments.of(new MatchedExpression("1+2-3*4/1"), "1+2-3*4", "/", "1")
        );
    }
}