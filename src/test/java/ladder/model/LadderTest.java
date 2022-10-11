package ladder.model;

import ladder.service.RandomLineGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class LadderTest {

    @Test
    @DisplayName("사다리 타기 게임이후에는 도착 라인에 도착해야 합니다.")
    void shouldArrive(){
        int numOfUsers = 5;
        int ladderLength = 20;
        Ladder ladder = new Ladder(getVerticalLines(numOfUsers,ladderLength),new LadderLength(ladderLength));

        LadderPosition position = ladder.play(0);

        assertThat(position.isArrived(new LadderLength(ladderLength))).isTrue();
    }


    private List<VerticalLine> getVerticalLines(int numberOfUsers , int ladderLength){
        RandomLineGenerator generator = new RandomLineGenerator(()-> new Random().nextBoolean());
        return IntStream.range(0,numberOfUsers).mapToObj((idx)->VerticalLine.mapHorizontalLineToVertical(generator.generate(numberOfUsers,new LadderLength(ladderLength)),idx)).collect(Collectors.toList());
    }

}
