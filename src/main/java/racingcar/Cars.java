package racingcar;

import java.util.List;

public final class Cars {

    private final List<Car> cars;

    public Cars(final List<Car> cars) {
        validateCars(cars);
        this.cars = cars;
    }

    private static void validateCars(final List<Car> cars) {
        if (cars == null) {
            throw new IllegalArgumentException(
                ExceptionMessageUtils.createdExceptionMessage("cars cannot be null"));
        }
        if (cars.isEmpty()) {
            throw new IllegalArgumentException(
                ExceptionMessageUtils.createdExceptionMessage("cars cannot be empty"));
        }
        if (cars.contains(null)) {
            throw new IllegalArgumentException(
                ExceptionMessageUtils.createdExceptionMessage("cars cannot contain null object"));
        }
    }

    public void move() {
        for (final Car car : cars) {
            car.move();
        }
    }
}
