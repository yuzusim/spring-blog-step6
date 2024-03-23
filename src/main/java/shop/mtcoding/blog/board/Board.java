package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String username;

    @CreationTimestamp
    private Timestamp createdAt;

    // 생성자 만들기 (필요한 것만)
    public Board(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }

    // 오브젝트 지향 프로그램이라서 업데이트 메서드를 만들어 주고 한번에 변경
    // 다른 곳에서 재사용하려면 DTO 이름을 적을 수 없다!
    public void update(BoardRequest.UpdateDTO reqDTO) {
        this.title = reqDTO.getTitle();
        this.content = reqDTO.getContent();
        this.username = reqDTO.getUsername();
    }

}
