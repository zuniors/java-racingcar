package racing.view;

import racing.car.CarDto;

import java.util.List;
import java.util.stream.IntStream;

public class ResultView {

    private static final char MOVE_MARK = '-';

    private ResultView() {}

    public static void printResultStatement(List<CarDto> cars) {
        System.out.println("실행 결과");
        printCurState(cars);
    }

    public static void printCurState(List<CarDto> cars) {
        cars.stream()
                .map(CarDto::getPosition)
                .forEach(ResultView::printCarMovement);

        printNewLine();
    }

    private static void printCarMovement(final int carPosition) {
        IntStream.rangeClosed(0, carPosition)
                .forEach(i -> System.out.print(MOVE_MARK));

        printNewLine();
    }

    private static void printNewLine() {
        System.out.println();
    }
}
