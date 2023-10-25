package project.meme.repository.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@EnableJpaAuditing
public class NoticeBoardRepositoryTest {

    @Autowired
    private MembersRepository membersRepository;
    @Autowired
    private NoticeBoardRepository noticeBoardRepository;

    private static Members members;
    private static NoticeBoard noticeBoard;

    @BeforeEach
        // 테스트 실행 전 실행
    void setUp() {
        members = new Members();
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

        noticeBoard = new NoticeBoard();
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
    }

    @Test
    @DisplayName("공지사항 게시글 등록 테스트")
    public void testCreatePosts_Notice() {
        NoticeBoard savedPost = noticeBoardRepository.findByNoticeBoardId(noticeBoard.getNoticeBoardId());
        org.junit.jupiter.api.Assertions.assertEquals(savedPost, noticeBoard);

    }

    @Test
    @DisplayName("공지사항 전체 조회")
    public void testFindAllPosts() {
        Members members1 = new Members();
        members1.setUserName("test");
        members1.setEmail("test1@gmail.com");
        members1.setNickname("testy");
        members1.setPassword("1234");
        members1.setConfirmPassword("1234");
        members1.setPhoneNumber("010-1234-5678");
        members1.setReportingCount(0);
        members1.setCreationDate(new Date());
        members1.setRole("ROLE_TEST");
        members1.setStatus("status_test");
        membersRepository.save(members1);

        Members members2 = new Members();
        members2.setUserName("test");
        members2.setEmail("test2@gmail.com");
        members2.setNickname("testy");
        members2.setPassword("1234");
        members2.setConfirmPassword("1234");
        members2.setPhoneNumber("010-1234-5678");
        members2.setReportingCount(0);
        members2.setCreationDate(new Date());
        members2.setRole("ROLE_TEST");
        members2.setStatus("status_test");
        membersRepository.save(members2);

        NoticeBoard noticeBoard1 = new NoticeBoard();
        noticeBoard1.setTitle("test title");
        noticeBoard1.setNickname("testy");
        noticeBoard1.setContent("I am creating test post");
        noticeBoard1.setMembers(members1);
        noticeBoard1.setLikeCount(0);
        noticeBoard1.setViewCount(0);
        noticeBoard1.setCommentCount(0);
        noticeBoard1.setFilePath(null);
        noticeBoard1.setCreatedBy(members1.getNickname());
        noticeBoard1.setModifiedBy(members1.getNickname());

        noticeBoardRepository.save(noticeBoard1);

        NoticeBoard noticeBoard2 = new NoticeBoard();
        noticeBoard2.setTitle("test title");
        noticeBoard2.setNickname("testy");
        noticeBoard2.setContent("I am creating test post");
        noticeBoard2.setMembers(members2);
        noticeBoard2.setLikeCount(0);
        noticeBoard2.setViewCount(0);
        noticeBoard2.setCommentCount(0);
        noticeBoard2.setFilePath(null);
        noticeBoard2.setCreatedBy(members2.getNickname());
        noticeBoard2.setModifiedBy(members2.getNickname());

        noticeBoardRepository.save(noticeBoard2);

        List<NoticeBoard> listPosts = noticeBoardRepository.findAll();
        Assertions.assertThat(listPosts.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("특정 게시글 조회_1.게시글 번호")
    public void testFindById() {
        // noticeBoardId로 검색한 값이 존재 하는 지 검사
        NoticeBoard foundPost = noticeBoardRepository.findByNoticeBoardId(noticeBoard.getNoticeBoardId());
        Assertions.assertThat(foundPost).isNotNull();
        Assertions.assertThat(foundPost.getTitle()).isEqualTo("test title");
        Assertions.assertThat(foundPost.getNickname()).isEqualTo("testy");
    }
}


// noticeBoardId로 찾은 값의 제목이 "test title"이 맞는지 테스트
//        Assertions.assertThat(foundPost.getTitle()).isEqualTo("test title");
// noticeBoardId로 찾은 값의 작성자가 일치하는지 확인
//        Assertions.assertThat(foundPost.getNickname()).isEqualTo("testy");

