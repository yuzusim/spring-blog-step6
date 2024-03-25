package shop.mtcoding.blog.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardJPARepository boardJPARepository;

    public void 글삭제(Integer boardId, Integer sessionUserId) {
        // 1. 조회 및 예외처리
        // 삭제와 업데이트는 조회하고 들어가기!
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        // 2. 권한처리
        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글 수정할 권한이 없습니다.");
        }
        boardJPARepository.deleteById(boardId);

    }

    public Board 글조회(int boardId){
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
        return board;
    }

    public void 글수정(int boardId, int sessionUserId, BoardRequest.UpdateDTO reqDTO) {
        // 1. 더티체킹 하기 위해 조회 및 예외처리
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("{게시글을 찾을 수 없습니다."));

        // 2. 권한처리
        if (sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }

        // 3. 글 수정하기
        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());

    } // 더티체킹


    //    public Board 게시글수정폼(Integer id, Integer id1) {
//    }
    @Transactional
    public void 글쓰기(BoardRequest.SaveDTO reqDTO, User sessionUser) {
        boardJPARepository.save(reqDTO.toEntity(sessionUser));

    }



}

