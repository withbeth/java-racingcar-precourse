package racingcar;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class CarsTest {

    // TODO
    private static final String EXCEPTION_MESSAGE_PREFIX = "[ERROR]";

    @Test
    void nullCarNames() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> ObjectFactory.getCarsInstance(null))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @Test
    void emptyCarNames() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> ObjectFactory.getCarsInstance(Collections.emptyList()))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

    @Test
    void containsInvalidCarName() {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> ObjectFactory.getCarsInstance(
                Arrays.asList("with", null, "beth", "", "foo", "foobar")))
            .withMessageContaining(EXCEPTION_MESSAGE_PREFIX);
    }

}
