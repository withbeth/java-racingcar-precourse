package racingcar;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(final String[] args) {
        // TODO : Create Views and Validation Util class
        final List<String> carNames = Arrays.asList("foo", "bar", "fifty", "beth");
        final int playCoins = 5;

        final Game game = new Game(carNames, playCoins);
        while (!game.isGameOver()) {
            game.play();
            System.out.println(game.getStatus());
        }
        System.out.println(game.getWinners());
    }
}
