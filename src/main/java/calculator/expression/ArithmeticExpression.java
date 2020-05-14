package calculator.expression;

import calculator.interpreter.Interpreter;
import calculator.interpreter.MatchedExpression;
import calculator.operator.Operator;

import java.util.Objects;

public class ArithmeticExpression extends Expression {
    private final Expression subExpression;
    private final Operator operator;

    protected ArithmeticExpression(MatchedExpression matchedExpression) {
        validate(matchedExpression);

        subExpression = Interpreter.interpret(matchedExpression.getSubExpressionString());
        operand = Integer.parseInt(matchedExpression.getOperandString());
        operator = Operator.of(matchedExpression.getOperatorString());
    }

    private void validate(MatchedExpression matchedExpression) {
        if(matchedExpression == null) {
            throw new IllegalArgumentException("MatchedExpression is null");
        }

        if(!matchedExpression.isValidExpression()) {
            throw new IllegalArgumentException("MatchedExpression is not arithmetic expression");
        }
    }

    public static ArithmeticExpression of(MatchedExpression matchedExpression) {
        return new ArithmeticExpression(matchedExpression);
    }

    @Override
    public int calculate() {
        return operator.operate(subExpression.calculate(), operand);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArithmeticExpression that = (ArithmeticExpression) o;
        return operand == that.operand &&
                Objects.equals(subExpression, that.subExpression) &&
                operator == that.operator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subExpression, operator, operand);
    }
}
