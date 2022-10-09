package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarsTest {

    // TODO
    private static final String EXCEPTION_MESSAGE_PREFIX = "[ERROR]";

    @Test
    void nullCarNames() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> CarFactory.getDefaultCars(null))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @Test
    void emptyCarNames() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> CarFactory.getDefaultCars(Collections.emptyList()))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @Test
    void containsInvalidCarName() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> CarFactory.getDefaultCars(
                Arrays.asList("with", null, "beth", "", "foo", "foobar")))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    void move(final int count) {
        final Car always50MoveCar = CarFactory.getCar("giant", () -> Distance.from(50),
            () -> true);
        final Car always10MoveCar = CarFactory.getCar("giant", () -> Distance.from(10),
            () -> true);
        final Cars cars = CarFactory.getCars(Arrays.asList(always50MoveCar, always10MoveCar));

        for (int i = 0; i < count; i++) {
            Distance prevAlways50MoveCarDistance = always50MoveCar.getDistance();
            Distance prevAlways10MoveCarDistance = always10MoveCar.getDistance();

            cars.move();

            assertThat(always50MoveCar.getDistance()).
                isEqualTo(prevAlways50MoveCarDistance.plus(Distance.from(50)));
            assertThat(always10MoveCar.getDistance())
                .isEqualTo(prevAlways10MoveCarDistance.plus(Distance.from(10)));

            prevAlways50MoveCarDistance = always50MoveCar.getDistance();
            prevAlways10MoveCarDistance = always10MoveCar.getDistance();
        }
    }

}
