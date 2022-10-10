package racingcar;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(final String[] args) {

        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        // TODO : Create Views and Validation Util class
        final List<String> carNames = Arrays.asList("foo", "bar", "fifty", "beth");
        System.out.println(carNames.toString());

        System.out.println("시도할 회수는 몇회인가요?");
        final int playCoins = 5;
        System.out.println(playCoins);

        OutputView.printDivider();
        final Game game = new Game(carNames, playCoins);
        OutputView.printGameResultTitle();
        while (!game.isGameOver()) {
            game.play();
            OutputView.printCarsStatus(game.getCarsStatus());
            OutputView.printDivider();
        }
        OutputView.printWinners(game.getWinners());
    }
}
