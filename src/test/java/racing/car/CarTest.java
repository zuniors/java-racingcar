package racing.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import racing.move.MoveStrategy;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("자동차 테스트")
class CarTest {
    @Test
    @DisplayName("정적 팩토리 메소드 테스트")
    void newInstance() {
        assertThatCode(Car::newInstance).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("무브 테스트")
    void move(final MoveStrategy moveStrategy, final boolean isMoveExpected) {
        Car car = Car.newInstance();
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

}