package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {

    @Autowired
    private BoardPersistRepository boardPersistRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findAll_test() {
       // given
        List<Board> boardList = boardPersistRepository.findAll();

       // when
        System.out.println("findAll_test/size : "+boardList.size());
        System.out.println("findAll_test/username : "+boardList.get(1).getUsername());

       // then
        assertThat(boardList.size()).isEqualTo(4);
        assertThat(boardList.get(1).getUsername()).isEqualTo("cos");

    }

    @Test
    public void save_test() {
       // given
//        String title = "제목5"; // 더미 4개 있으니까 이제 새로 넣을껀 5번쩨
//        String content = "내용5";
//        String username = "ssar";
//
//        System.out.println("title : "+title);
//        System.out.println("content : "+content);
//        System.out.println("username : "+username);


        // 2. 영속화 후 테스트 깔끔 그 자체!!
        Board board = new Board("제목5", "내용5", "ssar");

       // when
        boardPersistRepository.save(board);
        System.out.println("save_test : "+board);

       // then

    }


}
