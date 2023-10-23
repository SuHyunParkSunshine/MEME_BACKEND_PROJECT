package project.meme.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import project.meme.domain.board.NoticeBoard;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Integer> {

    // 게시글 등록
    // >> save

    // 게시글 전체 조회
    // >> findAll

    // 게시글 수정
    // >> save

    // 게시글 삭제
    void deleteByNoticeBoardId(Long noticeBoardId);

    // 특정 게시글 조회
    // 1. 게시글 번호로 조회
    NoticeBoard findByNoticeBoardId(Long noticeBoardId);

    // 2. 게시글 제목으로 조회
    NoticeBoard findByTitle(String title);

    // 3 게시글 작성자(이름 or 닉네임)로 조회
    NoticeBoard findByNickname(String nickname);
    NoticeBoard findByUserName(String userName);
}
