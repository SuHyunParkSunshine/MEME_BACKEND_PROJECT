package project.meme.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import project.meme.domain.Members;
import project.meme.domain.board.FreeBoard;
import project.meme.domain.board.Likes;
import project.meme.domain.board.NoticeBoard;

import java.util.List;

public interface BoardLikeRepository extends JpaRepository<Likes, Integer> {

    // 해당 유저가 해당 게시글에 좋아요를 누른 레이블이 존재하는 가??? 를 판단하기 위함.
    Likes findByMembersAndFreeBoard(Members members, FreeBoard freeBoard);
    Likes findByMembersAndNoticeBoard(Members members, NoticeBoard noticeBoard);

    // 이미 좋아요가 눌려있을 때 -> 레이블을 삭제해야함
    void deleteByMembersAndFreeBoard(Members members, FreeBoard freeBoard);
    void deleteByMembersAndNoticeBoard(Members members, NoticeBoard noticeBoard);

    // 좋아요 없을 때 -> 고냥 레이블 추가
    // >> save

    // 해당 계정이 "좋아요" 누른 게시판 반환
    List<Likes> findByMembersAndFreeBoardIsNotNull(Members members);
    List<Likes> findByMembersAndNoticeBoardIsNotNull(Members members);
}
