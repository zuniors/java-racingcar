package racing.car;

public class CarDto {
    private final int position;

    private CarDto(Car car) {
        this.position = car.curPosition();
    }

    public static CarDto of(Car car) {
        return new CarDto(car);
    }

    public int getPosition() {
        return position;
    }
}
