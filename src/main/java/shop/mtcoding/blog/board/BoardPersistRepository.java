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

    public List<Board> findAll(){
        // 이전쿼리
        // select * from board_tb order by id desc", Board.class
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
}
