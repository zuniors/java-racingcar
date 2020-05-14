package racing.move;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("랜덤 무브 전략 테스트")
class RandomMoveStrategyTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("랜덤 값이 4미만일 때 false, 4이상일 때 true 를 리턴하는지")
    void isMovable(final Random random, final boolean expected) {
        assertThat(new RandomMoveStrategy(random).isMovable())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> isMovable() {
        return Stream.of(
                Arguments.of(TestRandom.of(3), false),
                Arguments.of(TestRandom.of(4), true),
                Arguments.of(TestRandom.of(5), true)
        );
    }


    static class TestRandom extends Random {
        private final int fixedValue;

        private TestRandom(final int fixedValue) {
            this.fixedValue = fixedValue;
        }

        private static TestRandom of(final int fixedValue) {
            return new TestRandom(fixedValue);
        }

        @Override
        public int nextInt(int bound) {
            return fixedValue;
        }
    }
}