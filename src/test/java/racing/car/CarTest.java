package racing.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import racing.move.MoveStrategy;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("자동차 테스트")
class CarTest {

    @Test
    @DisplayName("of 정적 팩토레 메소드")
    void of() {
        assertThatCode(() -> Car.of("name")).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("null 혹은 빈 이름이 들어왔을 때 예외를 발생")
    void ofException(final String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Car.of(name));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("무브 테스트")
    void move(final MoveStrategy moveStrategy, final boolean isMoveExpected) {
        Car car = Car.of("name");
        int prevPosition = car.curPosition();

        car.move(moveStrategy);

        assertThat(car.curPosition()).isEqualTo(prevPosition + (isMoveExpected ? 1 : 0));
    }

    private static Stream<Arguments> move() {
        return Stream.of(
                Arguments.of((MoveStrategy)() -> true, true),
                Arguments.of((MoveStrategy)() -> false, false)
        );
    }

    @Test
    @DisplayName("선두인지 아닌지")
    void isLeader() {
        Car leaderCar = Car.of("선두");
        Car car = Car.of("선두아냐");

        leaderCar.move(() -> true);
        leaderCar.move(() -> true);

        int leadingPosition = 2;

        assertThat(leaderCar.isLeader(leadingPosition)).isTrue();
        assertThat(car.isLeader(leadingPosition)).isFalse();
    }
}