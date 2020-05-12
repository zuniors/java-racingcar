package calculator.operation;

import calculator.exception.ErrorMessage;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ArithmeticOperation {

    PLUS("+", Math::addExact),
    MINUS("-", Math::subtractExact),
    TIMES("*", Math::multiplyExact),
    DIVIDE("/", Math::floorDiv);

    private final String expression;
    private final BiFunction<Integer, Integer, Integer> function;

    private static final Map<String, ArithmeticOperation> OPERATIONS =
            Arrays.stream(ArithmeticOperation.values())
                    .collect(Collectors.toMap(ArithmeticOperation::getExpression, Function.identity()));

    ArithmeticOperation(final String expression, final BiFunction<Integer, Integer, Integer> function) {
        this.expression = expression;
        this.function = function;
    }

    private String getExpression() {
        return this.expression;
    }

    public static ArithmeticOperation fromExpression(final String expression) {
        if (!OPERATIONS.containsKey(expression)) {
            throw new IllegalArgumentException(String.format(ErrorMessage.NOT_SUPPORTED_ARITHMETIC, expression));
        }
        return OPERATIONS.get(expression);
    }

    public Integer operate(final Integer operandX, final Integer operandY) {
        return function.apply(operandX, operandY);
    }
}
