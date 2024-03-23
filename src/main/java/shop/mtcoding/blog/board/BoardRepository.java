package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;


    // 게시글 목록보기
    public List<Board> findAll(){
        // 이전쿼리 -> select * from board_tb order by id desc", Board.class
        // 리팩 -> 쿼리 내에서 반복적으로 "Board"를 사용하지 않고 간단히 b로 참조할 수 있게
        Query query =
                em.createQuery("select b from Board b order by b.id desc", Board.class);
        return  query.getResultList();
    }

    // 게시글 상세보기
    public Board findByIdJoinUser(int id){
        Query query =
                em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class);
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
        // 알아서 pk, fk 키 연결
    }

    public Board findById(int id){
        // 일단 조회
        // id, title, content, user_id(이질감), created_at
        Board board = em.find(Board.class, id);
        return board;

        // em.find(Board.class, id)는 EntityManager(em)을 사용하여
        // Board 클래스에서 주어진 ID와 일치하는 엔티티를 데이터베이스에서 검색
        // 검색된 Board 객체가 반환되어 해당 메서드의 호출자에게 반환

    }

    // 게시글 쓰기
    @Transactional
    public Board save(Board board) {
        em.persist(board);
        return board;
    }

    // 게시글 삭제
    @Transactional
    public void deleteById(int id){
        Query query =
                em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
