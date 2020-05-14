package calculator.expression;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static calculator.helper.Generator.matchedExpressionOf;
import static org.assertj.core.api.Assertions.*;

@DisplayName("수식 테스트")
class ArithmeticExpressionTest {

    @Test
    @DisplayName("of 사용으로 객체 생성 확인")
    void constructor() {
        ArithmeticExpression arithMeticExpression = ArithmeticExpression.of(matchedExpressionOf("1", "+", "2"));

        assertThatCode(() -> ArithmeticExpression.of(matchedExpressionOf("1", "+", "2")))
                .doesNotThrowAnyException();
        assertThat(arithMeticExpression).isNotNull();
    }

    @Test
    @DisplayName("of 객체 생성시 null로 인한 exception 테스트")
    void ofExceptionNull() {
        assertThatIllegalArgumentException().isThrownBy(() -> ArithmeticExpression.of(null));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("of 객체 생성시 exception 테스트")
    void ofException(final String subExpression, final String operator, final String operand) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ArithmeticExpression.of(matchedExpressionOf(subExpression, operator, operand)));
    }

    private static Stream<Arguments> ofException() {
        return Stream.of(
                Arguments.of("", "+", "2"),
                Arguments.of(null, "+", "2"),
                Arguments.of("1", null, "2"),
                Arguments.of("1", "+", ""),
                Arguments.of("1", "+", null)
        );
    }

    @ParameterizedTest
    @DisplayName("테스트를 위한 equals 메소드 테스트")
    @CsvSource({"1+2,+,3", "1,+,2"})
    void equals(final String subExp, final String operator, final String operand) {
        ArithmeticExpression arithmeticExpression = ArithmeticExpression.of(matchedExpressionOf(subExp, operator, operand));
        ArithmeticExpression otherExp = ArithmeticExpression.of(matchedExpressionOf(subExp, operator, operand));

        assertThat(arithmeticExpression).isEqualTo(otherExp);
    }

    @ParameterizedTest
    @DisplayName("테스트를 위한 not equals 메소드 테스트")
    @CsvSource({"1+2,+,3,2+1,+,3", "1,+,2,2,+,1"})
    void equals(final String subExp, final String operator, final String operand,
                final String anotherSubExp, final String anotherOperator, final String anotherOperand) {
        ArithmeticExpression arithmeticExpression = ArithmeticExpression.of(matchedExpressionOf(subExp, operator, operand));
        ArithmeticExpression otherExp = ArithmeticExpression.of(matchedExpressionOf(anotherSubExp, anotherOperator, anotherOperand));

        assertThat(arithmeticExpression).isNotEqualTo(otherExp);
    }

    @ParameterizedTest(name = "{0} {1} {2} = {3}")
    @CsvSource({"1,+,2,3", "1,-,2,-1", "1,*,2,2", "2,/,1,2", "1+2,+,3,6"})
    @DisplayName("수식의 계산이 올바른지")
    void operate(final String subExp, final String operator, final String operand, final int expected) {
        ArithmeticExpression arithmeticExpression = ArithmeticExpression.of(matchedExpressionOf(subExp, operator, operand));

        assertThat(arithmeticExpression.calculate()).isEqualTo(expected);
    }
}