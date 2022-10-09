package racingcar;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {

    // TODO
    private static final String EXCEPTION_MESSAGE_PREFIX = "[ERROR]";

    private static final MovePolicy GIANT_MOVE_POLICY = () -> Distance.from(75);
    private static final MoveCondition ALWAYS_MOVE_CONDITION = () -> true;

    @Test
    void nullOrSpace() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> CarFactory.getDefaultCar(null))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> CarFactory.getDefaultCar(""))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @ParameterizedTest
    @ValueSource(strings = {"foobar", "foobarx", "hogehoge"})
    void longName(final String longName) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> CarFactory.getDefaultCar(longName))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @ParameterizedTest
    @ValueSource(strings = {"f", "fo", "foo", "foob", "fooba"})
    void validName(final String validName) {
        assertThatCode(() -> CarFactory.getDefaultCar(validName))
            .doesNotThrowAnyException();
    }

    @Test
    void nullMovePolicy() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> CarFactory.getCar(
                "beth",
                null,
                CarFactory.getDefaultCarMoveCondition()))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @Test
    void nullMoveCondition() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> CarFactory.getCar(
                "beth",
                CarFactory.getDefaultCarMovePolicy(),
                null))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @Test
    void move_defaultPolicy_defaultCondition() {
        final Car car = CarFactory.getDefaultCar("beth");
        assertThat(car.getDistance()).isEqualTo(Distance.ZERO);
        car.move();
        assertThat(car.getDistance()).isIn(Distance.ZERO, Distance.ONE);
    }

    @Test
    void move_defaultPolicy_customCondition() {
        final Car car = CarFactory.getCar(
            "beth",
            CarFactory.getDefaultCarMovePolicy(),
            ALWAYS_MOVE_CONDITION);
        assertThat(car.getDistance()).isEqualTo(Distance.ZERO);
        car.move();
        assertThat(car.getDistance()).isEqualTo(Distance.ONE);
    }

    @Test
    void move_customPolicy_defaultCondition() {
        final Car car = CarFactory.getCar(
            "beth",
            GIANT_MOVE_POLICY,
            CarFactory.getDefaultCarMoveCondition());
        assertThat(car.getDistance()).isEqualTo(Distance.ZERO);
        car.move();
        assertThat(car.getDistance()).isIn(Distance.ZERO, Distance.from(75));
    }

    @Test
    void move_customPolicy_customCondition() {
        final Car car = CarFactory.getCar(
            "beth",
            GIANT_MOVE_POLICY,
            ALWAYS_MOVE_CONDITION);

        assertThat(car.getDistance()).isEqualTo(Distance.ZERO);

        car.move();

        assertThat(car.getDistance()).isEqualTo(Distance.from(75));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    void move_movingForwardBasedOnPolicyAndCondition(final int count) {
        final Car car = CarFactory.getCar(
            "beth",
            GIANT_MOVE_POLICY,
            ALWAYS_MOVE_CONDITION);
        for (int i = 0; i < count; i++) {
            Distance prevDistance = car.getDistance();

            car.move();

            assertThat(car.getDistance()).isEqualTo(prevDistance.plus(Distance.from(75)));

            prevDistance = car.getDistance();
        }

    }

}
