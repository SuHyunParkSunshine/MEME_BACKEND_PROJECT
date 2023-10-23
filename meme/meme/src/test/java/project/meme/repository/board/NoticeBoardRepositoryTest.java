package project.meme.repository.board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import project.meme.domain.Members;
import project.meme.domain.board.NoticeBoard;
import project.meme.repository.MembersRepository;

import java.util.Date;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@EnableJpaAuditing
public class NoticeBoardRepositoryTest {

    @Autowired
    private MembersRepository membersRepository;
    @Autowired
    private NoticeBoardRepository noticeBoardRepository;

    @Test
    @DisplayName("공지사항 게시글 등록 테스트")
    public void testCreatePosts_Notice() {
        Members members = new Members();
        members.setUserName("test");
        members.setEmail("test12@gmail.com");
        members.setNickname("testy");
        members.setPassword("1234");
        members.setConfirmPassword("1234");
        members.setPhoneNumber("010-1234-5678");
        members.setReportingCount(0);
        members.setCreationDate(new Date());
        members.setRole("ROLE_TEST");
        members.setStatus("status_test");
        membersRepository.save(members);

        NoticeBoard noticeBoard = new NoticeBoard();
        noticeBoard.setTitle("test title");
        noticeBoard.setNickname("testy");
        noticeBoard.setContent("I am creating test post");
        noticeBoard.setMembers(members);
        noticeBoard.setLikeCount(0);
        noticeBoard.setViewCount(0);
        noticeBoard.setCommentCount(0);
        noticeBoard.setFilePath(null);
        noticeBoard.setCreatedBy(members.getNickname());
        noticeBoard.setModifiedBy(members.getNickname());

        noticeBoardRepository.save(noticeBoard);

        NoticeBoard foundPost = noticeBoardRepository.findByNoticeBoardId(noticeBoard.getNoticeBoardId());
        // noticeBoardId로 검색한 값이 존재하는지 검사
        Assertions.assertThat(foundPost).isNotNull();
        // noticeBoardId로 찾은 값의 제목이 "test title"이 맞는지 테스트
        Assertions.assertThat(foundPost.getTitle()).isEqualTo("test title");
        // noticeBoardId로 찾은 값의 작성자가 일치하는지 확인
        Assertions.assertThat(foundPost.getNickname()).isEqualTo("testy");
    }
}
