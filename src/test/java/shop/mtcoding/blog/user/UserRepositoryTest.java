package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void updateById_test() {
       // given
        int id = 1;
        String password = "999";
        String email = "ssar@nate.com";

       // when
        userRepository.updateById(id, password, email);
        em.flush();

       // then

    }

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        userRepository.findById(id);
        em.flush();

        // then

    }

    // 로그인 username, password 테스트
//    @Test
//    public void findByIdUsername_test() {
//        // given
//        String username = "ssar";
//        String password = "1234";
//
//        // when
//         User user = userRepository.findByIdAndPassword(username, password);
//
//        // then
//        assertThat(user.getUsername()).isEqualTo("ssar");
//        // assertThat(user.getPassword()).isEqualTo("12345"); // 틀려보면 NoResultException > 잡아야함
//
//    }

}
