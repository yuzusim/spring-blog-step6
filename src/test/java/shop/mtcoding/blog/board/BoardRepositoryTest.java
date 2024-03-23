package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void _test() {
       // given

       // when

       // then

    }

    @Test
    public void findById_test() {
       // given
        int id = 1;

        System.out.println("start - 1");
        Board board = boardRepository.findById(id);
        System.out.println("start - 2");
        System.out.println(board.getUser().getId());
        System.out.println("start - 3");
        System.out.println(board.getUser().getUsername());
        // 실제로 그 객체가 전달되지 않음
        // 프록시 패턴이 있어서
        // user_id는 조회가 됐으나(포함됐으나), getUsername은 (포함) 안 되었다.
        // 그러나... null값이 뜨는게 아니라, 값이 잘 뜬다!!
        // 그러나... select 가 2번 일어난다.

    }

}
