package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardRequest {
    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
        private String username;
    }

    @Data
    public static class SaveDTO {
        private String title;
        private String content;
        private String username;

        // DTO를 Entity로 바꾸는 메서드 만들기 인서트 할때만 필요, 3개만 들고
        // 이거 save하려면 Entity로 바꿔야함!
        public Board toEntity(User user) { // 유저 객체 넣어 줌
            // 비영속 Board 객체에 User 객체를 넣어줘야 함 -> 세션을 받으면 됨
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(user) // PK만 들어 있어도 됨 그러나 위험한 코드, 비추
                    .build();
        }

    }
}
