package racingcar;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {

    private static final String EXCEPTION_MESSAGE_PREFIX = "[ERROR]";

    @Test
    void nullOrSpace() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Car(null))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @ParameterizedTest
    @ValueSource(strings = {"foobar", "foobarx", "hogehoge"})
    void longName(final String longName) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Car(longName))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @ParameterizedTest
    @ValueSource(strings = {"f", "fo", "foo", "foob", "fooba"})
    void validName(final String validName) {
        assertThatCode(() -> new Car(validName))
            .doesNotThrowAnyException();
    }

    @Test
    void invalidRandomValueCreator() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Car("beth", null))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -5, -2, -1, 10, 11, 12, 15})
    void invalidRandomValue(final int randomValue) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Car("beth", () -> randomValue))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void validRandomValue(final int randomValue) {
        assertThatCode(() -> new Car("beth", () -> randomValue))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void move_RandomValueLessThanThreshold(final int randomValue) {
        final Car car = new Car("beth", () -> randomValue);

        // Test consistency also
        for (int i = 0; i < 100000; i++) {

            final int distanceBeforeMove = car.getDistance();

            car.move();

            assertThat(distanceBeforeMove).isEqualTo(car.getDistance());

        }
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void move_RandomValueGreaterThanThreshold(final int randomValue) {
        final Car car = new Car("beth", () -> randomValue);

        // Test consistency also
        for (int i = 0; i < 100000; i++) {

            final int distanceBeforeMove = car.getDistance();

            car.move();

            assertThat(distanceBeforeMove + 1).isEqualTo(car.getDistance());

        }
    }

}
