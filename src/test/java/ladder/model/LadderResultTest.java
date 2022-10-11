package ladder.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LadderResultTest {

    @Test
    void shouldValidate(){
        assertThatThrownBy(()->new LadderResult(Arrays.asList("꽝","당첨",null))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()->new LadderResult(Arrays.asList("꽝","당첨",""))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldGetGameResult(){
        LadderResult result = new LadderResult(Arrays.asList("제로콜라", "곱창", "떡볶이"));

        assertThat(result.getResult(new LadderPosition(0))).isEqualTo("제로콜라");
        assertThat(result.getResult(new LadderPosition(1))).isEqualTo("곱창");
        assertThat(result.getResult(new LadderPosition(2))).isEqualTo("떡볶이");
    }
}
