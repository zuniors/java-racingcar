package racing.car;

public class CarDto {
    private int position;

    private CarDto(Car car) {
        this.position = car.curPosition();
    }

    public static CarDto of(Car car) {
        return new CarDto(car);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(final int position) {
        this.position = position;
    }
}
