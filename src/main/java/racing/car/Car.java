package racing.car;

import racing.move.MoveStrategy;
import racing.util.StringUtil;

public class Car {
    private String name;
    private int curPosition;

    private Car() {}

    public Car(final String name) {
        if (StringUtil.isEmpty(name)) {
            throw new IllegalArgumentException("Name can't be null or empty string");
        }

        this.name = name;
    }

    public static Car newInstance() {
        return new Car();
    }

    public static Car of(final String name) {
        return new Car(name);
    }

    public int curPosition() {
        return curPosition;
    }

    public void move(MoveStrategy moveStrategy) {
        if (moveStrategy.isMovable()) {
            curPosition++;
        }
    }
}
