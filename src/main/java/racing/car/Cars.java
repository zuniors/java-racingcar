package racing.car;

import racing.move.MoveStrategy;
import racing.util.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    public static final String NAME_SPLITTER = ",";

    private final List<Car> cars;

    private Cars(final String namesStr) {
        validate(namesStr);

        String[] names = namesStr.split(NAME_SPLITTER);

        cars = Arrays.stream(names)
                .map(Car::of)
                .collect(Collectors.toList());
    }

    private void validate(final String names) {
        if (StringUtil.isEmpty(names)) {
            throw new IllegalArgumentException("NamesInput is null or empty");
        }
    }

    public static Cars init(final String namesStr) {
        return new Cars(namesStr);
    }

    public void moveAll(MoveStrategy moveStrategy) {
        cars.forEach(car -> car.move(moveStrategy));
    }

    public List<CarDto> getCarDtos() {
        return cars.stream()
                .map(CarDto::of)
                .collect(Collectors.toList());
    }

    public List<CarDto> getWinners() {
        int leadingPosition = leadingPosition();

        return cars.stream()
                .filter(car -> car.isLeader(leadingPosition))
                .map(CarDto::of)
                .collect(Collectors.toList());
    }

    private int leadingPosition() {
        return cars.stream()
                .mapToInt(Car::curPosition)
                .max()
                .orElseThrow(RuntimeException::new);
    }
}
