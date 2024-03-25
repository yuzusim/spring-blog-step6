package shop.mtcoding.blog.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;

import java.util.Optional;

@RequiredArgsConstructor
@Service // ioc 등록 됨
public class UserService {
    // 이제 컨트롤러는 서비스에 의존, 서비스는 레포에 의존 - 의존관계

    private final UserJPARepository userJPARepository;

    public User 회원수정(int id, UserRequest.UpdateDTO reqDTO) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다."));
        // 예외처리 옵셔널이 붙어있어서 강제 처리
        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());

        // userJPARepository.save(user) 이걸 사용해도 됨
        return user;
    } // 더티체킹


    public User 회원수정폼(int id) {
        User user = userJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다."));
        return user;

    }

    // 조회라 트랜젝션 안 붙여도 됨!
    public User 로그인(UserRequest.LoginDTO reqDTO) {
        User sessionUser = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다.")); // orElseThrow 값이 null이면 이라는 뜻
        return sessionUser; // 세션에 저장
    }

    @Transactional // userJPARepository 가 들고 있으면 안돼!! 한번에 여러개를 할 수 있으니까 서비스에서 통으로 관리해야 함
    public void 회원가입(UserRequest.JoinDTO reqDTO) {
        // 1. 유효성 검사(컨트롤러의 책임)
        // save 하다가 날 수있는 오류의 종류가 엄청나게 많다
        // 트라이캐치로 퉁치면 어디서 오류가 나는지 알 수 없다.

        // 2. 유저네임 중복검사 (서비스 체크) DB 연결이 필요함
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername()); // 유저네임으로 조회

        // isPresent가 있으면 비정상
        if (userOP.isPresent()) { // 유저 중복
            throw new Exception400("중복된 유저 네임입니다."); // 예외처리
        }

        // 아닌 경우 정상적으로 저장
        userJPARepository.save(reqDTO.toEntity());
    }
}