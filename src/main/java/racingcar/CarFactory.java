package racingcar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CarFactory {

    private static final String CAR_NAME_DELIMITER = ",";

    private CarFactory() {
    }

    public static MoveCondition getDefaultCarMoveCondition() {
        return new SixtyPercentRandomMoveCondition();
    }

    public static MovePolicy getDefaultCarMovePolicy() {
        return new OneStepMovePolicy();
    }

    public static Car getCar(
        final String carName,
        final MovePolicy movePolicy,
        final MoveCondition moveCondition
    ) {
        return new Car(carName, movePolicy, moveCondition);
    }

    public static Car getDefaultCar(final String carName) {
        return getCar(carName, getDefaultCarMovePolicy(), getDefaultCarMoveCondition());
    }

    public static Cars getCars(final List<Car> cars) {
        return new Cars(cars);
    }

    public static Cars getDefaultCars(final String carNames) {
        return getCars(
            mapToCarList(carNames, getDefaultCarMovePolicy(), getDefaultCarMoveCondition()));
    }

    public static Cars getDefaultCars(final List<String> carNames) {
        return getCars(
            mapToCarList(carNames, getDefaultCarMovePolicy(), getDefaultCarMoveCondition()));
    }

    private static List<Car> mapToCarList(
        final List<String> carNames,
        final MovePolicy movePolicy,
        final MoveCondition moveCondition
    ) {
        if (carNames == null) {
            return Collections.emptyList();
        }
        final List<Car> cars = new ArrayList<>(carNames.size());
        for (final String carName : carNames) {
            cars.add(getCar(carName, movePolicy, moveCondition));
        }
        return cars;
    }

    private static List<Car> mapToCarList(
        final String carNames,
        final MovePolicy movePolicy,
        final MoveCondition moveCondition
    ) {
        if (carNames == null || carNames.isEmpty()) {
            return Collections.emptyList();
        }
        final List<Car> cars = new ArrayList<>();
        for (final String carName : carNames.split(CAR_NAME_DELIMITER)) {
            cars.add(getCar(carName.trim(), movePolicy, moveCondition));
        }
        return cars;
    }

}
