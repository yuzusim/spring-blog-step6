package shop.mtcoding.blog.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardJPARepository boardJPARepository;


    // 로그인을 하고 게시글의 주인이면 isOwne가 true가 된다 !
//        boolean isOwner  = false; // 게시글 주인 여부
//        if (sessionUser != null) { // 세션 유저가 null이 아니면 (로그인 했으면 )
//            if (sessionUser.getId() == board.getUser().getId()) {
//                isOwner = true;
//            }
//        }
//
//        request.setAttribute("isOwner", isOwner);
//        request.setAttribute("board", board);

    // board, isOwner
    public Board 글상세보기(int boardId, User sessionUser) {
        Board board = boardJPARepository.findByIdJoinUser(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        // 게시글 주인 여부
        boolean isBoardOwner = false;
        if (sessionUser != null) {
            if (sessionUser.getId() == board.getUser().getId()) {
                isBoardOwner = true;
            }
        }

        board.setBoardOwner(isBoardOwner);

        // 레이지 로딩
        // 댓글은 forEach문 돌리기 (N이니까)
        board.getReplies().forEach(reply -> {
            boolean isReplyOwner = false;

            // 댓글 주인 여부
            if (sessionUser != null) {
                if (reply.getUser().getId() == sessionUser.getId()) {
                    isReplyOwner = true;
                }
            }
            reply.setReplyOwner(isReplyOwner);
        });

        return board;
    }

    public List<Board> 글목록조회() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return boardJPARepository.findAll(sort);
        // return에 sort 객체 안 넣어주면 DESC 안 됨
    }

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

    public Board 글조회(int boardId) {
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

