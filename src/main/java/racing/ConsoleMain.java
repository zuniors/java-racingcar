package racing;

import racing.view.InputView;
import racing.view.ResultView;

public class ConsoleMain {
    public static void main(String[] args) {
        int numOfCars = InputView.inputNumOfCars();
        int round = InputView.inputRound();

        RacingGame racingGame = RacingGame.init(numOfCars, round);
        ResultView.printResultStatement(racingGame.curState());

        while(!racingGame.isRaceOver()) {
            racingGame.race();

            ResultView.printCurState(racingGame.curState());
        }
    }
}
