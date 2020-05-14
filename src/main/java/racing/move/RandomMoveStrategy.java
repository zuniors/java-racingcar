package racing.move;

import java.util.Random;

public class RandomMoveStrategy implements MoveStrategy {
    private static final int MAXIMUM_NUM = 10;
    private static final int MOVE_STANDARD = 4;

    private final Random random;

    public RandomMoveStrategy(Random random) {
        this.random = random;
    }

    @Override
    public boolean isMovable() {
        return random.nextInt(MAXIMUM_NUM) >= MOVE_STANDARD;
    }
}
