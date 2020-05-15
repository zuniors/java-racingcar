package racing.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_CAR_NAMES_STATEMENT = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String INPUT_ROUND_STATEMENT = "시도할 회수는 몇 회 인가요?";

    private InputView() {}

    public static String getNames() {
        printStatement(INPUT_CAR_NAMES_STATEMENT);

        return SCANNER.nextLine();
    }

    public static int getRound() {
        printStatement(INPUT_ROUND_STATEMENT);

        return takeIntInput();
    }

    private static void printStatement(final String statement) {
        System.out.println(statement);
    }

    private static int takeIntInput() {
        return Integer.parseInt(SCANNER.nextLine());
    }
}
