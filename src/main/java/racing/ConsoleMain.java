package racing;

import racing.view.InputView;
import racing.view.ResultView;

public class ConsoleMain {
    public static void main(String[] args) {
        String names = InputView.getNames();
        int round = InputView.getRound();

        RacingGame racingGame = RacingGame.init(names, round);
        ResultView.printResultStatement(racingGame.curState());

        while(!racingGame.isRaceOver()) {
            racingGame.race();

            ResultView.printCurState(racingGame.curState());
        }

        ResultView.printWinners(racingGame.getWinners());
    }
}
