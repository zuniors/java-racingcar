package racing.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_NUM_OF_CARS_STATEMENT = "자동차 대수는 몇 대 인가요?";
    private static final String INPUT_ROUND_STATEMENT = "시도할 회수는 몇 회 인가요?";

    private InputView() {}

    public static int inputNumOfCars() {
        printStatement(INPUT_NUM_OF_CARS_STATEMENT);

        return getInt();
    }

    public static int inputRound() {
        printStatement(INPUT_ROUND_STATEMENT);

        return getInt();
    }

    private static void printStatement(final String statement) {
        System.out.println(statement);
    }

    private static int getInt() {
        return Integer.parseInt(SCANNER.nextLine());
    }
}
