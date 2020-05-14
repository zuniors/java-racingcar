package calculator.operator;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Operator {
    PLUS("+", Math::addExact),
    MINUS("-", Math::subtractExact),
    MULTIPLY("*", Math::multiplyExact),
    DIVIDE("/", Math::floorDiv);

    private static final Map<String, Operator> OPERATORS =
            Arrays.stream(Operator.values())
                    .collect(Collectors.toMap(Operator::getSymbol, Function.identity()));

    private final String symbol;
    private final BiFunction<Integer, Integer, Integer> function;

    Operator(final String symbol, BiFunction<Integer, Integer, Integer> function) {
        this.symbol = symbol;
        this.function = function;
    }

    public static Operator of(final String symbol) {
        if(!OPERATORS.containsKey(symbol)) {
            throw new IllegalArgumentException("Not supported operation : " + symbol);
        }

        return OPERATORS.get(symbol);
    }

    public int operate(int x, int y) {
        return function.apply(x, y);
    }

    private String getSymbol() {
        return symbol;
    }
}
