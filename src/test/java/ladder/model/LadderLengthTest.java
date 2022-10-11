package ladder.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LadderLengthTest {

    @Test
    void shouldVaidateLadderLegnth(){
        assertThrows(IllegalArgumentException.class,()->new LadderLength(-1));
    }

    @Test
    void shouldIncreaseLength(){
        LadderLength length = new LadderLength(1);

        length.increase();

        Assertions.assertThat(length).isEqualTo(new LadderLength(2));
    }


}
