package racingcar;

import java.util.Objects;

public class Distance {

    public static final Distance ZERO = Distance.from(0);
    public static final Distance ONE = Distance.from(1);

    private final int value;

    private Distance(final int value) {
        this.value = value;
    }

    public static Distance from(final int value) {
        return new Distance(value);
    }

    public Distance plus(final Distance other) {
        return from(this.value + other.value);
    }

    public boolean isLessThan(final Distance other) {
        return this.value < other.value;
    }

    public boolean isGreaterThanOrEqual(final Distance other) {
        return this.value >= other.value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Distance distance = (Distance) o;
        return value == distance.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Distance{" +
            "value=" + value +
            '}';
    }
}