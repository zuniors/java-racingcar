package racing.car;

public class CarDto {
    private final String name;
    private final int position;

    private CarDto(Car car) {
        this.name = car.getName();
        this.position = car.curPosition();
    }

    public static CarDto of(Car car) {
        return new CarDto(car);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
