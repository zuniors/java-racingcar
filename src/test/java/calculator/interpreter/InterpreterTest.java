package calculator.interpreter;

import calculator.expression.ArithmeticExpression;
import calculator.expression.Expression;
import calculator.expression.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static calculator.helper.Generator.matchedExpressionOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("인터프리터 테스트")
class InterpreterTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("식이 비어 있거나 null 일때 IllegalArgumentException 예외를 던지는지")
    void validate(final String exp) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Interpreter.interpret(exp));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("올바른 수식을 생성하는지")
    void interpret(final String expString, final Expression exp) {
        assertThat(Interpreter.interpret(expString))
                .isEqualTo(exp);
    }

    private static Stream<Arguments> interpret() {
        return Stream.of(
                Arguments.of("1", Number.of("1")),
                Arguments.of("1 + 2", ArithmeticExpression.of(matchedExpressionOf("1", "+", "2"))),
                Arguments.of("1 + 2 + 3", ArithmeticExpression.of(matchedExpressionOf("1 + 2", "+", "3")))
        );
    }
}