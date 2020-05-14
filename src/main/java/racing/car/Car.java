package racing.car;

import racing.move.MoveStrategy;

public class Car {
    private int curPosition;

    private Car() {}

    public static Car newInstance() {
        return new Car();
    }

    public int curPosition() {
        return curPosition;
    }

    public void move(MoveStrategy moveStrategy) {
        if(moveStrategy.isMovable()) {
            curPosition++;
        }
    }
}
