package racing.view;

import racing.car.CarDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {
    private static final char MOVE_MARK = '-';
    public static final String WINNER_JOIN_MARK = ", ";
    public static final String WINNER_STATEMENT = "가 최종 우승했습니다.";

    private ResultView() {}

    public static void printResultStatement(List<CarDto> cars) {
        System.out.println("실행 결과");
        printCurState(cars);
    }

    public static void printCurState(List<CarDto> cars) {
        cars.forEach(ResultView::printCarMovement);

        printNewLine();
    }

    private static void printCarMovement(final CarDto car) {
        System.out.print(car.getName() + " : ");

        IntStream.rangeClosed(0, car.getPosition())
                .forEach(i -> System.out.print(MOVE_MARK));

        printNewLine();
    }

    private static void printNewLine() {
        System.out.println();
    }

    public static void printWinners(List<CarDto> winners) {
        String winner = winners.stream()
                .map(CarDto::getName)
                .collect(Collectors.joining(WINNER_JOIN_MARK));

        System.out.println(winner + WINNER_STATEMENT);
    }
}
