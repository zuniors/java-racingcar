package racing;

import racing.car.CarDto;
import racing.car.Cars;
import racing.move.MoveStrategy;
import racing.move.RandomMoveStrategy;
import sun.plugin.dom.exception.InvalidAccessException;

import java.util.List;
import java.util.Random;

public class RacingGame {
    public static final int MINIMUM_ROUND = 0;

    private int round;
    private final Cars cars;
    private final MoveStrategy moveStrategy;

    private RacingGame(final int numOfCars, final int round) {
        this(numOfCars, round, new RandomMoveStrategy(new Random()));
    }

    private RacingGame(final int numOfCars, final int round, final MoveStrategy moveStrategy) {
        validate(round);

        this.round = round;
        this.cars = Cars.init(numOfCars);
        this.moveStrategy = moveStrategy;
    }

    public RacingGame(final String namesStr, final int round) {
        this(namesStr, round, new RandomMoveStrategy(new Random()));
    }

    public RacingGame(final String namesStr, final int round, final MoveStrategy moveStrategy) {
        validate(round);

        this.round = round;
        this.cars = Cars.init(namesStr);
        this.moveStrategy = moveStrategy;
    }

    private void validate(int round) {
        if(round <= MINIMUM_ROUND) {
            throw new IllegalArgumentException("Racing game round must be greater than " + MINIMUM_ROUND);
        }
    }

    public static RacingGame init(final int numOfCars, final int round, final MoveStrategy moveStrategy) {
        return new RacingGame(numOfCars, round, moveStrategy);
    }

    public static RacingGame init(final String namesStr, final int round, final MoveStrategy moveStrategy) {
        return new RacingGame(namesStr, round, moveStrategy);
    }

    public static RacingGame init(final int numOfCars, final int round) {
        return new RacingGame(numOfCars, round);
    }

    public static RacingGame init(final String namesStr, final int round) {
        return new RacingGame(namesStr, round);
    }

    public void race() {
        if(isRaceOver()) {
            throw new InvalidAccessException("Can't race anymore");
        }

        cars.moveAll(moveStrategy);
        round--;
    }

    public boolean isRaceOver() {
        return round <= MINIMUM_ROUND;
    }

    public List<CarDto> curState() {
        return cars.getCarDtos();
    }
}
