package calculator.utils;

public class StringUtil {
    private static final String BLANK = " ";
    private static final String EMPTY = "";

    private StringUtil() {}

    public static String removeBlank(final String origin) {
        return origin.replace(BLANK, EMPTY);
    }

    public static boolean isEmpty(final String string) {
        return string == null || string.isEmpty();
    }
}
