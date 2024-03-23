package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void updateById_test() {
       // given
        int id = 1;
        String title = "title1";
        String content = "content";

       // when
        boardRepository.updateById(id, title, content);
        em.flush();
        // 업데이트가 된 건지 확인이 안 되기 때문에 (트랜젝션 종료 후 쿼리가 날아가서)
        // 실제 코드는 작성할 필요가 없다. 트랜젝션 종료될 거라!
        // em.flush를 꼭 해줘야함!


        // then

    }

    @Test
    public void deleteById_test() {
       // given
        int id = 1;

       // when
        boardRepository.deleteById(id);

       // then
        System.out.println("deleteById_test : "+boardRepository.findAll().size());

    }

    @Test
    public void findAll_test() {
       // given

       // when
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername()); // 여기서 레이지 로딩 발동
        });

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
