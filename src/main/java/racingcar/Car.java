package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

public final class Car {

    // TODO
    private static final String EXCEPTION_MESSAGE_PREFIX = "[ERROR]";
    private static final String SPACE = " ";

    private static final int MAX_NAME_LENGTH = 5;
    private static final int MIN_RANDOM_NUMBER = 0;
    private static final int MAX_RANDOM_NUMBER = 9;
    private static final int MOVABLE_RANDOM_NUMBER_THRESHOLD = 4;
    private static final RandomValueCreator DEFAULT_RANDOM_VALUE_CREATOR = () ->
        Randoms.pickNumberInRange(MIN_RANDOM_NUMBER, MAX_RANDOM_NUMBER);

    private final String name;
    private final RandomValueCreator randomValueCreator;
    private int distance;

    public Car(final String name) {
        this(name, DEFAULT_RANDOM_VALUE_CREATOR);
    }

    public Car(final String name, final RandomValueCreator randomValueCreator) {
        validateName(name);
        validateRandomValueCreator(randomValueCreator);
        this.name = name;
        this.randomValueCreator = randomValueCreator;
        this.distance = 0;
    }

    private static String createdExceptionMessage(final String message) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(EXCEPTION_MESSAGE_PREFIX);
        stringBuilder.append(SPACE);
        stringBuilder.append(message);
        return stringBuilder.toString();
    }

    private static void validateRandomValueCreator(final RandomValueCreator randomValueCreator) {
        if (randomValueCreator == null) {
            throw new IllegalArgumentException(
                createdExceptionMessage("RandomValueCreator instance cannot be null"));
        }
        validateRandomValue(randomValueCreator.create());
    }

    private static void validateRandomValue(final int randomValue) {
        if (MIN_RANDOM_NUMBER > randomValue) {
            throw new IllegalArgumentException(
                createdExceptionMessage(
                    String.format("randomValue cannot be less than %d", MIN_RANDOM_NUMBER)));
        }
        if (MAX_RANDOM_NUMBER < randomValue) {
            throw new IllegalArgumentException(
                createdExceptionMessage(
                    String.format("randomValue cannot be greater than %d", MAX_RANDOM_NUMBER)));
        }
    }

    private static void validateName(final String name) {
        if (name == null) {
            throw new IllegalArgumentException(createdExceptionMessage("car name cannot be null"));
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException(createdExceptionMessage("car name cannot be empty"));
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(createdExceptionMessage("car name is too long"));
        }
    }

    public void move() {
        if (!isMovable()) {
            return;
        }
        distance++;
    }

    private boolean isMovable() {
        return isRandomValueGreaterThanThreshold();
    }

    private boolean isRandomValueGreaterThanThreshold() {
        final int randomValue = randomValueCreator.create();
        validateRandomValue(randomValue);
        return randomValue >= MOVABLE_RANDOM_NUMBER_THRESHOLD;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Car{" +
            "name='" + name + '\'' +
            ", distance=" + distance +
            '}';
    }
}
