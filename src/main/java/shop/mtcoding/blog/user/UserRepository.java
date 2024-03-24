package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public User findById(int id){
        User user = em.find(User.class, id);
        return user;
    }

    public User findByUsernameAndpassword(UserRequest.LoginDTO reqDTO){
        Query query =
                em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class);
        query.setParameter("username", reqDTO.getUsername());
        query.setParameter("password", reqDTO.getPassword());
        return (User) query.getSingleResult();
    }

    @Transactional
    public User save(User user){
        em.persist(user);
        return user;
    }

    @Transactional
    public User updateById(int id, String password, String email){
        User user = findById(id);
        user.setPassword(password);
        user.setEmail(email);
        return user;
    }// 더티체킹


}
