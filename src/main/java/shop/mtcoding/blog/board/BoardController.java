package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository;

    // 게시글 수정 폼
    @GetMapping("/board/{id}/update-form")
    public String uodateForm(@PathVariable Integer id, HttpServletRequest request) {
//        Board board = boardPersistRepository.findById(id);
//        request.setAttribute("board", board);
        return "/board/update-form";
    }

    // @PathVariable을 사용하여 URL 경로에서 id를 추출하고,
    // 이를 통해 업데이트할 게시글의 ID를 얻습니다.
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, BoardRequest.UpdateDTO reqDTO) {
//        Board board = boardPersistRepository.findById(id);
//        board.update(board);
//        boardPersistRepository.updateById(id, reqDTO);
        return "redirect:/board/"+id;
    }

    // 게시글 삭제
    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) {
//        boardPersistRepository.deleteById(id);
        return "redirect:/";
    }

    // 게시글 목록보기
    @GetMapping("/")
    public String index(HttpServletRequest request) {
//        List<Board> boardList = boardPersistRepository.findAll();
//        request.setAttribute("boardList", boardList);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    // 게시글 쓰기
    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO) {
//        boardPersistRepository.save(reqDTO.toEntity());
        // DTO 받아서 toEntity 메서드로 호출, 객체로 바뀜
        // Board 객체를 toEntity() 담가!! 영속화 시킴!!
        return "redirect:/";
    }

    // 게시글 상세보기
    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardRepository.findByIdJoinUser(id);
        request.setAttribute("board", board);
        return "board/detail";
    }


}
