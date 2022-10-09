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

    private static final MovePolicy DEFAULT_MOVE_POLICY = new OneStepMovePolicy();
    private static final MovePolicy GIANT_MOVE_POLICY = () -> Distance.from(75);
    private static final MoveCondition DEFAULT_MOVE_CONDITION = new SixtyPercentRandomMoveCondition();
    private static final MoveCondition ALWAYS_MOVE_CONDITION = () -> true;

    @Test
    void nullOrSpace() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> ObjectFactory.getDefaultCarInstance(null))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> ObjectFactory.getDefaultCarInstance(""))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @ParameterizedTest
    @ValueSource(strings = {"foobar", "foobarx", "hogehoge"})
    void longName(final String longName) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> ObjectFactory.getDefaultCarInstance(longName))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @ParameterizedTest
    @ValueSource(strings = {"f", "fo", "foo", "foob", "fooba"})
    void validName(final String validName) {
        assertThatCode(() -> ObjectFactory.getDefaultCarInstance(validName))
            .doesNotThrowAnyException();
    }

    @Test
    void nullMovePolicy() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Car("beth", null, DEFAULT_MOVE_CONDITION))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @Test
    void nullMoveCondition() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Car("beth", DEFAULT_MOVE_POLICY, null))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @Test
    void move_defaultPolicy_defaultCondition() {
        final Car car = ObjectFactory.getDefaultCarInstance("beth");
        assertThat(car.getDistance()).isEqualTo(Distance.ZERO);
        car.move();
        assertThat(car.getDistance()).isIn(Distance.ZERO, Distance.ONE);
    }

    @Test
    void move_defaultPolicy_customCondition() {
        final Car car = new Car("beth", DEFAULT_MOVE_POLICY, ALWAYS_MOVE_CONDITION);
        assertThat(car.getDistance()).isEqualTo(Distance.ZERO);
        car.move();
        assertThat(car.getDistance()).isEqualTo(Distance.ONE);
    }

    @Test
    void move_customPolicy_defaultCondition() {
        final Car car = new Car("beth", GIANT_MOVE_POLICY, DEFAULT_MOVE_CONDITION);
        assertThat(car.getDistance()).isEqualTo(Distance.ZERO);
        car.move();
        assertThat(car.getDistance()).isIn(Distance.ZERO, Distance.from(75));
    }

    @Test
    void move_customPolicy_customCondition() {
        final Car car = new Car("beth", GIANT_MOVE_POLICY, ALWAYS_MOVE_CONDITION);
        assertThat(car.getDistance()).isEqualTo(Distance.ZERO);
        car.move();
        assertThat(car.getDistance()).isEqualTo(Distance.from(75));
    }

}
