package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {

    @Autowired
    private BoardJPARepository boardJPARepository;

    @Autowired
    private EntityManager em;

    //deleteById
    @Test
    public void deleteById_test() {
        //given
        int id = 1;

        //when
        boardJPARepository.deleteById(id);
        em.flush();


        //then


    }

    //findAll (sort)
    @Test
    public void findAll_test() {
        //given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        //when
        List<Board> boardList = boardJPARepository.findAll(sort);

        //then
        System.out.println("findAll_test : " + boardList);

    }

    //findByIdJoinUser
    @Test
    public void findByIdJoinUse_test() {
        // given
        int id = 1;

        // when
        //Board board = boardJPARepository.findByIdJoinUser(id);

        // then
//        System.out.println("findByIdJoinUse_test : "+board.getTitle());
//        System.out.println("findByIdJoinUse_test : "+board.getUser().getUsername());

//        System.out.println("findByIdJoinUser_test" + board.getUser());
//        System.out.println("findByIdJoinUser_test" + board.getTitle());
//        System.out.println("findByIdJoinUser_test" + board.getContent());

        // Assortion 코드도 필요함

    }

    // findById
    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Optional<Board> boardOP = boardJPARepository.findById(id);

        if (boardOP.isPresent()) {
            Board board = boardOP.get();
            System.out.println("findById_test : " + board.getUser());
            System.out.println("findById_test : " + board.getTitle());
            System.out.println("findById_test : " + board.getContent());
        }

        // then

    }

    // save
    @Test
    public void save_test() {
        // given
        User sessionUser = User.builder().id(1).build(); // 빌더 패턴을 사용하여 User 객체를 생성
        Board board = Board.builder() // 빌더 패턴을 사용하여 Board 객체를 생성
                .title("제목5") // 새로운 게시글의 제목을 "제목5"로 설정
                .content("내용5") // 새로운 게시글의 제목을 "제목5"로 설정
                .user(sessionUser) // sessionUser는 현재 세션에서 로그인한 사용자
                .build();

        // when
        boardJPARepository.save(board); // 호출하여 게시글을 데이터베이스에 저장

        // then
        System.out.println("save_test : "+board.getId()); // 게시글의 ID를 출력하여 확인

    }
}