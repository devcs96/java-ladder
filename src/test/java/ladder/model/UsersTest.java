package ladder.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static ladder.model.User.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class UsersTest {


    @Test
    @DisplayName("가로선(x * y)을 수직선(y * x)으로 매핑해야 합니다.")
    void shouldMapLine() {
        List<HorizontalLine> horizontalLine = List.of(new HorizontalLine(List.of(new LineUnit(), new LineUnit(), new LineUnit())));
        Users users = new Users(List.of(createUserWithName("A"), createUserWithName("B"),createUserWithName("C")));

        List<VerticalLine> verticalLines = users.mapToVertical(horizontalLine);

       assertThat(verticalLines.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("추가하고자 하는 라인 행수와 유저 개수가 맞지 않을떄는 예외가 발생해야 합니다.")
    void shouldNotMapLine_whenDifferentNum() {
        List<HorizontalLine> horizontalLine = List.of(new HorizontalLine(List.of(new LineUnit(), new LineUnit(), new LineUnit())));
        Users users = new Users(List.of(createUserWithName("A"), createUserWithName("B")));

        assertThatThrownBy(() -> users.mapToVertical(horizontalLine)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldFindUserStartPositionByUsername(){
        User userA = createUserWithName("testA");
        User userB = createUserWithName("testB");
        Users users = new Users(List.of(userA,userB));

        assertThat(users.findStartPositionByUsername(new UserName("testA"))).isEqualTo(List.of(0));
        assertThat(users.findStartPositionByUsername(new UserName("testB"))).isEqualTo(List.of(1));
    }

    @Test
    void shouldNotFindUserByUsername_whenUsernameNotExist(){
        User userA = createUserWithName("testA");
        User userB = createUserWithName("testB");
        Users users = new Users(List.of(userA,userB));

        assertThatThrownBy(()->users.findStartPositionByUsername(new UserName("hello"))).isInstanceOf(IllegalArgumentException.class);
    }



}
