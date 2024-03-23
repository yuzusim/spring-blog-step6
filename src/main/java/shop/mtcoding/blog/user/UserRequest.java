package shop.mtcoding.blog.user;

import lombok.Data;

public class UserRequest {
    @Data
    public static class JoinDTO{
        private String username;
        private String password;
        private String email;

        public User toEntity(){
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        } // @Builder 있어야 사용 가능(알쏭달쏭 하다면 재 확인체크!!)
    }

    @Data
    public static class LoginDTO{
        private String username;
        private String password;
    }
}
