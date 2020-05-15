package racing;

import racing.view.InputView;
import racing.view.ResultView;

public class ConsoleMain {
    public static void main(String[] args) {
        int numOfCars = InputView.getNumOfCars();
        int round = InputView.getRound();

        RacingGame racingGame = RacingGame.init(numOfCars, round);
        ResultView.printResultStatement(racingGame.curState());

        while(!racingGame.isRaceOver()) {
            racingGame.race();

            ResultView.printCurState(racingGame.curState());
        }
    }
}
