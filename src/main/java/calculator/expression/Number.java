package calculator.expression;


import calculator.utils.StringUtil;

import java.util.Objects;

public class Number extends Expression {

    private Number(final String numberString) {
        validate(numberString);

        this.operand = Integer.parseInt(StringUtil.removeBlank(numberString));
    }

    public static Number of(final String numberString) {
        return new Number(numberString);
    }

    @Override
    public int calculate() {
        return operand;
    }

    private void validate(String numberString) {
        if(StringUtil.isEmpty(numberString)) {
            throw new IllegalArgumentException("Number string is null or empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number that = (Number) o;
        return operand == that.operand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand);
    }
}
