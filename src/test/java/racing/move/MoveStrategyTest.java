package racing.move;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("move strategy 테스트")
class MoveStrategyTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("move strategy 에 따라서 올바른 불린값을 리턴하는지")
    void move(final MoveStrategy moveStrategy, final boolean expected) {
        assertThat(moveStrategy.isMovable()).isEqualTo(expected);
    }

    private static Stream<Arguments> move() {
        return Stream.of(
                Arguments.of((MoveStrategy)() -> true, true),
                Arguments.of((MoveStrategy)() -> false, false)
        );
    }
}