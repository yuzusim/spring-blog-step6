package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

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


}
