package racing.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import racing.move.MoveStrategy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Cars 테스트")
class CarsTest {

    @Test
    @DisplayName("이름 배열을 받는 of 정적 팩토리 메소드 테스트")
    void of() {
        assertThatCode(() -> Cars.init("a,b,c"))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("of 정적 팩토리 메소드에 올바르지 않은 인풋이 들어왔을때")
    void ofException(final String namesStr) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Cars.init(namesStr));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("cars 움직이기 테스트")
    void moveAll(final MoveStrategy moveStrategy, final boolean isMovingExpected) {
        Cars cars = Cars.init("1,2");

        int prevPosition = cars.getCarDtos()
                .get(0)
                .getPosition();

        cars.moveAll(moveStrategy);

        int curPosition = cars.getCarDtos()
                .get(0)
                .getPosition();

        assertThat(curPosition).isEqualTo(prevPosition + (isMovingExpected ? 1 : 0));
    }

    private static Stream<Arguments> moveAll() {
        return Stream.of(
                Arguments.of((MoveStrategy)() -> true, true),
                Arguments.of((MoveStrategy)() -> false, false)
        );
    }

    @Test
    @DisplayName("리더 구하기")
    void getWinner() {
        Cars cars = Cars.init("1,2,3");

        assertThat(cars.getWinners().size()).isEqualTo(3);

        cars.moveAll(new TrueTrueFalseStrategy());

        List<String> winnerNames = cars.getWinners()
                .stream()
                .map(CarDto::getName)
                .collect(Collectors.toList());

        assertThat(winnerNames.size()).isEqualTo(2);
        assertThat(winnerNames).containsExactly("1", "2");

    }

    private static class TrueTrueFalseStrategy implements MoveStrategy {
        int num;

        @Override
        public boolean isMovable() {
            return ++num / 3 == 0;
        }
    }
}