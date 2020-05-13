package racingcar;

import racingcar.exception.ErrorMessage;
import racingcar.moving.MovingStrategy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Racing {

    private List<Car> cars;
    private MovingStrategy movingStrategy;

    static Racing of(final int numOfCar, final MovingStrategy movingStrategy) {
        return new Racing(numOfCar, movingStrategy);
    }
    private Racing(final int numOfCar, final MovingStrategy movingStrategy) {
        validateInput(numOfCar, movingStrategy);
        this.movingStrategy = movingStrategy;
        createCars(numOfCar);
    }

    private void validateInput(final int input, final MovingStrategy movingStrategy) {
        if (input < 0 || movingStrategy == null) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PARAMETER);
        }
    }

    private void createCars(final int numOfCar) {
        cars = Stream.generate(Car::of)
                .limit(numOfCar)
                .collect(Collectors.toList());
    }

    List<Car> run() {
        cars.forEach(this::moveCar);
        return cars;
    }

    private void moveCar(final Car car) {
        car.move(movingStrategy);
    }
}
