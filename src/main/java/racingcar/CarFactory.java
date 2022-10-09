package racingcar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CarFactory {

    private static final MovePolicy DEFAULT_CAR_MOVE_POLICY = new OneStepMovePolicy();
    private static final MoveCondition DEFAULT_CAR_MOVE_CONDITION = new SixtyPercentRandomMoveCondition();

    private CarFactory() {
    }

    public static Car getDefaultCarInstance(final String carName) {
        return new Car(carName, DEFAULT_CAR_MOVE_POLICY, DEFAULT_CAR_MOVE_CONDITION);
    }

    public static Cars getCarsInstance(final List<String> carNames) {
        return new Cars(mapToCarList(carNames));
    }

    private static List<Car> mapToCarList(final List<String> carNames) {
        if (carNames == null) {
            return Collections.emptyList();
        }
        final List<Car> cars = new ArrayList<>(carNames.size());
        for (final String carName : carNames) {
            cars.add(getDefaultCarInstance(carName));
        }
        return cars;
    }

}
