package project.meme.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import project.meme.domain.board.FreeBoard;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer> {

    // 게시글 등록
    // >> save

    // 게시글 전체 조회
    // >> findAll

    // 게시글 수정
    // >> save

    // 게시글 삭제
    void deleteByFreeBoardId(Long freeBoardId);

    // 특정 게시글 조회
    //  1. 게시글 번호로 조회
    FreeBoard findByFreeBoardId(Long freeBoardId);

    //  2. 게시글 제목으로 조회
    FreeBoard findByTitle(String title);

    //  3. 게시글 작성자로 조회
    FreeBoard findByNickname(String nickname);
}
