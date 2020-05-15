package racing.car;

import racing.move.MoveStrategy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cars {
    public static final int MINIMUM_NUM_OF_CARS = 0;

    private final List<Car> cars;

    private Cars(int numbOfCars) {
        validate(numbOfCars);

        cars = Stream.generate(Car::newInstance)
                .limit(numbOfCars)
                .collect(Collectors.toList());
    }

    private void validate(int numbOfCars) {
        if(numbOfCars <= MINIMUM_NUM_OF_CARS) {
            throw new IllegalArgumentException("Number of car must be greater than " + MINIMUM_NUM_OF_CARS);
        }
    }

    public static Cars init(int numOfCars) {
        return new Cars(numOfCars);
    }

    public void moveAll(MoveStrategy moveStrategy) {
        cars.forEach(car -> car.move(moveStrategy));
    }

    public List<CarDto> getCarDtos() {
        return cars.stream()
                .map(CarDto::of)
                .collect(Collectors.toList());
    }
}
