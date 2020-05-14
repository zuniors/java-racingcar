package calculator.interpreter;

import calculator.utils.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchedExpression {
    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("(.+)\\b([+\\-*/])(-?\\d+)");
    private static final int SUB_EXPRESSION_GROUP_INDEX = 1;
    private static final int OPERATOR_GROUP_INDEX = 2;
    private static final int OPERAND_GROUP_INDEX = 3;

    private final Matcher matcher;

    public MatchedExpression(String expressionString) {
        validate(expressionString);

        this.matcher = EXPRESSION_PATTERN.matcher(StringUtil.removeBlank(expressionString));
        this.matcher.matches();
    }

    private static void validate(String expressionString) {
        if(StringUtil.isEmpty(expressionString)) {
            throw new IllegalArgumentException("Expression is not valid : " + expressionString);
        }
    }

    public boolean isValidExpression() {
        return matcher.matches();
    }

    public String getSubExpressionString() {
        return matcher.group(SUB_EXPRESSION_GROUP_INDEX);
    }

    public String getOperatorString() {
        return matcher.group(OPERATOR_GROUP_INDEX);
    }

    public String getOperandString() {
        return matcher.group(OPERAND_GROUP_INDEX);
    }
}
