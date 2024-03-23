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


//    @Test
//    public void updateById_test() {
//        // given
//        int id = 1;
//        String title = "제목1";
//        String content = "내용1";
//        String username = "bori";
//
//        // when
//        boardNativeRepository.updateById(id, title, content, username);
//
//        // then
//        Board board = boardNativeRepository.findById(id);
//        System.out.println("updateById_test : "+board);
//        assertThat(board.getTitle()).isEqualTo("제목1");
//        assertThat(board.getContent()).isEqualTo("내용1");
//        assertThat(board.getUsername()).isEqualTo("bori");
//
//    }
//

    // 게시글 수정하기 테스트
    @Test
    public void updateById_test() {
       // given
        int id = 1;
        String title = "제목수정1";

       // when
        Board board = boardPersistRepository.findById(id);
        board.setTitle(title); // 더티체킹
        em.flush();

       // then
    }

    // 게시글 상세보기 테스트
    @Test
    public void deleteById_test() {
        // given
        int id = 1;

        // when
        boardPersistRepository.deleteById(id);

        // then

    }
    
    // 얘 안씀
    @Test
    public void deleteByIdV2_test() {
       // given
        int id = 1;

       // when
        boardPersistRepository.deleteByIdV2(id);

        // 이 라인 쿼리. 트랜젝션 종료되지 않았지만 강제로 날려보냄
        em.flush();

       // then
        Board board = boardPersistRepository.findById(id);
        System.out.println("deleteByIdV2_test : "+board);

    }

    // 게시글 상세보기 테스트
    @Test
    public void findById_test() {
       // given
        int id = 1;

       // when
        Board board = boardPersistRepository.findById(id);
        em.clear(); // 비우기, 2회 날리는 쿼리 (사용할 필요 없음! 궁금해서)
        boardPersistRepository.findById(id); // 동일한 id를 조회해서, 캐싱이 되었기 때문에 1번 달아가는 쿼리!
                System.out.println("findById_test : "+board);

       // then
        assertThat(board.getTitle()).isEqualTo("제목1");
        assertThat(board.getContent()).isEqualTo("내용1");

    }

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
