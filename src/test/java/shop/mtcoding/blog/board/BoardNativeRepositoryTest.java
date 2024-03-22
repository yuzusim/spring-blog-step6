package shop.mtcoding.blog.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(BoardNativeRepository.class)
@DataJpaTest
public class BoardNativeRepositoryTest {
    @Autowired
    private BoardNativeRepository boardNativeRepository;

    // 게시글 목록보기 테스트
    @Test
    public void findAll_test() {
       // given

       // when
        List<Board> boardList = boardNativeRepository.findAll();

       // then
        System.out.println("findAll_test/size : "+boardList.size());
        System.out.println("findAll_test/username : "+boardList.get(0).getUsername());

        assertThat(boardList.size()).isEqualTo(4);
        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");

    }
}
