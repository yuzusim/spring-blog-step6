package shop.mtcoding.blog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserJPARepository extends JpaRepository<User, Integer> {

    // 간단한 쿼리 작성하기(join 가능)
    // @Query("select u from User u where u.username = :username and u.password = :password")
    // 쿼리 메서드 -> 복잡도만 올라감, 그냥 쿼리 작성하기
    // 추상 메서드 생성
    Optional<User> findByUsernameAndPassword(@Param("username")String username, @Param("password") String password);
    Optional<User> findByUsername(@Param("username") String username);
}
