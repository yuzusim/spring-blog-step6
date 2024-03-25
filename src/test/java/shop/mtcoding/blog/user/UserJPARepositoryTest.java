package shop.mtcoding.blog.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

// import 필요 없음 JPARepository는 ioc에 띄워줌
@DataJpaTest
public class UserJPARepositoryTest {

    @Autowired
    private UserJPARepository userJPARepository;


    @Test
    public void findByUsernameAndPassword_test(){
        //given
        String username = "ssar";
        String password = "1234";

        //when
        userJPARepository.findByUsernameAndPassword(username, password);

        //then

    }

    @Test
    public void findAll_test(){
        // given

        // when
        userJPARepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        // then

    }

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
//        User user = userJPARepository.findById(id);
//        System.out.println("findById_test : "+user.getUsername());

        // Optional null처리하는 객체(nullPointException이 안 뜰 수 있다)
        // Optional<User> 객체를 사용하여 사용자를 조회
        Optional<User> userOP = userJPARepository.findById(id);

        //  isPresent() 메서드를 사용하여 Optional 객체가 값(NonNull)을
        // 포함하고 있는지 확인
        if (userOP.isPresent()) { // 존재하면
            User user = userOP.get(); // 꺼내기
            System.out.println("findById_test : " + user.getUsername());
        }

        // then
    }

    @Test
    public void save_test(){
        // given
        // 유저 객체가 필요함
        // 빌더 덕분에 생성자를 건드릴 필요가 없다!
        User user = User.builder()
                .username("happy")
                .password("1234")
                .email("happy@nate.com")
                .build();

        // when
        userJPARepository.save(user);

        // then
    }

}
