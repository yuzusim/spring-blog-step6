package shop.mtcoding.blog.reply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

import java.util.List;

/* 2가지 전략
 * 1. user는 one 관계 - > join
 *    board는 many 관계 -> 조회 1번 더하기 -> 객체 2개 -> DTO에 담기
 * 2. many 관계 -> 양방향 맵핑
 * */
@DataJpaTest
public class ReplyJPARepositoryTest {

    @Autowired
    private ReplyJPARepository replyJPARepository;

    @Test
    public void save_test() {
       // given
        Reply reply = Reply.builder()
                .board(Board.builder().id(1).build())
                .user(User.builder().id(1).build())
                .comment("댓글5")
                .build();


       // when
        replyJPARepository.save(reply);

       // then

    }


}
