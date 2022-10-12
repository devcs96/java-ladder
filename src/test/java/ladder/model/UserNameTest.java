package ladder.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserNameTest {

    @Test
    void shouldValidateUsername_lengthCond() {
        Assertions.assertThatThrownBy(() -> new UserName("hellow")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldValidateUsername_nullEmptyCond() {
        Assertions.assertThatThrownBy(() -> new UserName(null)).isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> new UserName("   ")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void should(){
        assertThat(new UserName(UserName.ALL_USER_NAME).isAllUser()).isTrue();
    }

}
