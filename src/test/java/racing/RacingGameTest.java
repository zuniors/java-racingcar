package racing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import racing.move.MoveStrategy;
import sun.plugin.dom.exception.InvalidAccessException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("레이싱 게임 테스트")
class RacingGameTest {

    @Test
    @DisplayName("초기화")
    void init() {
        assertThatCode(() -> RacingGame.init(1, 1))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource(value = {"1,-1", "0,1", "0,-1"})
    @DisplayName("초기화 실패 테스트")
    void initFail(final int numOfCars, final int round) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> RacingGame.init(numOfCars, round));
    }

    @Test
    @DisplayName("게임 오버 테스트")
    void isGameOver() {
        RacingGame racingGame = RacingGame.init(1, 2);

        assertThat(racingGame.isRaceOver()).isFalse();
        racingGame.race();

        assertThat(racingGame.isRaceOver()).isFalse();
        racingGame.race();

        assertThat(racingGame.isRaceOver()).isTrue();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("레이스 테스트")
    void race(final MoveStrategy moveStrategy, final int beforePosition, final int afterPosition) {
        RacingGame racingGame = RacingGame.init(2, 1, moveStrategy);

        racingGame.curState()
                .forEach(carDto -> assertThat(carDto.getPosition()).isEqualTo(beforePosition));

        racingGame.race();

        racingGame.curState()
                .forEach(carDto -> assertThat(carDto.getPosition()).isEqualTo(afterPosition));
    }

    private static Stream<Arguments> race() {
        return Stream.of(
                Arguments.of((MoveStrategy) () -> true, 0, 1),
                Arguments.of((MoveStrategy) () -> false, 0, 0)
        );
    }

    @Test
    @DisplayName("더이상 레이스를 진행할 수 없음")
    void raceException() {
        RacingGame racingGame = RacingGame.init(1, 1);

        assertThatCode(racingGame::race).doesNotThrowAnyException();
        assertThatThrownBy(racingGame::race).isInstanceOf(InvalidAccessException.class);
    }
}