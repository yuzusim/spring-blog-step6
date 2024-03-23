package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em;

    // 게시글 상세보기
    public Board findById(int id){
        Board board = em.find(Board.class, id); // 앞이 클래스, 뒤가 pk
        return board;
        // em.find(Board.class, id)를 찾으면, Board 로 바꾼다.
        // find 메소드는 단일 엔티티 객체를 조회할 때 사용되며,
        // 주로 특정 ID를 가진 엔티티를 찾을 때 사용
    }

    // 게시글 목록보기
    public List<Board> findAll(){
        // 이전쿼리 -> select * from board_tb order by id desc", Board.class
        // 리팩 -> 쿼리 내에서 반복적으로 "Board"를 사용하지 않고 간단히 b로 참조할 수 있게
        Query query =
                em.createQuery("select b from Board b order by b.id desc", Board.class);
        return query.getResultList();
    }

    @Transactional
    public Board save(Board board){

        em.persist(board);
        return board;
    }

    @Transactional
    public void deleteById(int id){
        Query query =
                em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional // 중첩 @Transactional !!
    public void deleteByIdV2(int id) {
        Board board = findById(id);

        // 근데 remove가 어떻게 동작하는지 궁금하니 테스트 해보자!
        em.remove(board);
    }


//    @Transactional
//    public void updateById(int id, String title, String content, String username){
//        Query query =
//                em.createNativeQuery("update board_tb set title=?, content=?, username=? where id=?");
//        query.setParameter(1, title);
//        query.setParameter(2, content);
//        query.setParameter(3, username);
//        query.setParameter(4, id);
//
//        query.executeUpdate();
//    }


}
