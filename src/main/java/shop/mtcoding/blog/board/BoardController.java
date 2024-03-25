package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    //private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final HttpSession session;

    // 게시글 수정 폼
    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) throws Exception {
        Board board = boardService.글조회(id);
        request.setAttribute("board", board);
        return "/board/update-form";
    }

    // @PathVariable을 사용하여 URL 경로에서 id를 추출하고,
    // 이를 통해 업데이트할 게시글의 ID를 얻습니다.
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글수정(id, sessionUser.getId(), reqDTO );
        return "redirect:/board/"+id;
    }

    // 게시글 삭제
    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) {
        // 권한, 인증 체크 지금은 생략
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글삭제(id, sessionUser.getId());
        return "redirect:/";
    }

    // 게시글 목록보기
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardService.글목록조회();
        request.setAttribute("boardList", boardList);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    // 게시글 쓰기
    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        // 권한 체크는 생략
        boardService.글쓰기(reqDTO, sessionUser);
        return "redirect:/";
    }

    // 게시글 상세보기
    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.글상세보기(id, sessionUser);

        request.setAttribute("board", board);
        System.out.println("서버 사이드 랜더링 직전에는 Board와 User만 조회된 상태이다~~~~~~");

        return "board/detail";
    }

}
