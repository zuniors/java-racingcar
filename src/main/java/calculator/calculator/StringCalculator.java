package calculator.calculator;


import calculator.exception.ErrorMessage;
import calculator.operation.ArithmeticOperation;
import calculator.util.IntegerUtil;
import calculator.util.StringUtil;

public class StringCalculator {

    private static final String DELIMITER = " ";
    private static final int OFFSET_OF_OPERATOR = 0;
    private static final int OFFSET_OF_OPERAND = 1;

    public static StringCalculator of() {
        return new StringCalculator();
    }

    private StringCalculator() {}

    public Integer run(final String input) {
        this.validateNullOrEmpty(input);
        String[] values = input.split(DELIMITER);
        return this.calculate(values);
    }

    private void validateNullOrEmpty(final String input) {
        if (StringUtil.isEmpty(input)) {
            throw new IllegalArgumentException(ErrorMessage.IS_NULL_OR_EMPTY);
        }
    }

    private int calculate(final String[] values) {
        this.validateInputValues(values);
        int result = StringUtil.toInt(values[0]);
        for (int i = 1; i < values.length; i += 2) {
            ArithmeticOperation operator = ArithmeticOperation.fromExpression(getToken(values, i, OFFSET_OF_OPERATOR));
            result = operator.operate(result, StringUtil.toInt(getToken(values, i, OFFSET_OF_OPERAND)));
        }
        return result;
    }

    private void validateInputValues(final String[] values) {
        if (IntegerUtil.isZero(values.length % 2)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_SUPPORTED_FORMAT);
        }
    }

    private String getToken(final String[] values, final int index, final int offset) {
        try {
            return values[index + offset];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_SUPPORTED_FORMAT);
        }
    }
}
