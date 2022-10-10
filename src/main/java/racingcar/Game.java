package racingcar;

import java.util.ArrayList;
import java.util.List;

public final class Game {

    private final Cars cars;
    private int coins;

    public Game(final List<String> carNames, final int playCoins) {
        this.cars = CarFactory.getDefaultCars(carNames);
        this.coins = playCoins;
    }

    private static List<CarStatusDto> mapToCarStatuses(final List<Car> cars) {
        final List<CarStatusDto> result = new ArrayList<>(cars.size());
        for (final Car car : cars) {
            result.add(new CarStatusDto(car.getName(), car.getDistance()));
        }
        return result;
    }

    private static List<String> mapToCarNames(final List<Car> cars) {
        final List<String> result = new ArrayList<>(cars.size());
        for (final Car car : cars) {
            result.add(car.getName());
        }
        return result;
    }

    public void play() {
        if (isGameOver()) {
            return;
        }
        cars.move();
        coins--;
    }

    public boolean isGameOver() {
        return coins <= 0;
    }

    public List<CarStatusDto> getStatus() {
        return mapToCarStatuses(cars.getCars());
    }

    public List<String> getWinners() {
        return mapToCarNames(cars.getFarthestMovedCars());
    }
}
