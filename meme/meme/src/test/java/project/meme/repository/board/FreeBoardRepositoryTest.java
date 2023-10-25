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
import project.meme.domain.board.FreeBoard;
import project.meme.domain.board.NoticeBoard;
import project.meme.repository.MembersRepository;

import java.util.Date;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@EnableJpaAuditing
public class FreeBoardRepositoryTest {

    @Autowired
    private MembersRepository membersRepository;
    @Autowired
    private FreeBoardRepository freeBoardRepository;

    private static Members members;
    private static FreeBoard freeBoard;

    @BeforeEach
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

        freeBoard = new FreeBoard();
        freeBoard.setTitle("test title");
        freeBoard.setNickname("testy");
        freeBoard.setContent("I am creating test post");
        freeBoard.setMembers(members);
        freeBoard.setLikeCount(0);
        freeBoard.setViewCount(0);
        freeBoard.setCommentCount(0);
        freeBoard.setFilePath(null);
        freeBoard.setCreatedBy(members.getNickname());
        freeBoard.setModifiedBy(members.getNickname());

        freeBoardRepository.save(freeBoard);
    }

    @Test
    @DisplayName("자유게시글 등록 테스트")
    public void testCreatePosts_Free() {
        FreeBoard savedPost = freeBoardRepository.findByFreeBoardId(freeBoard.getFreeBoardId());
        org.junit.jupiter.api.Assertions.assertEquals(savedPost, freeBoard);
    }

    @Test
    @DisplayName("자유게시글 전체 조회")
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

        FreeBoard freeBoard1 = new FreeBoard();
        freeBoard1.setTitle("test title");
        freeBoard1.setNickname("testy");
        freeBoard1.setContent("I am creating test post");
        freeBoard1.setMembers(members1);
        freeBoard1.setLikeCount(0);
        freeBoard1.setViewCount(0);
        freeBoard1.setCommentCount(0);
        freeBoard1.setFilePath(null);
        freeBoard1.setCreatedBy(members1.getNickname());
        freeBoard1.setModifiedBy(members1.getNickname());

        freeBoardRepository.save(freeBoard1);

        FreeBoard freeBoard2 = new FreeBoard();
        freeBoard2.setTitle("test title");
        freeBoard2.setNickname("testy");
        freeBoard2.setContent("I am creating test post");
        freeBoard2.setMembers(members2);
        freeBoard2.setLikeCount(0);
        freeBoard2.setViewCount(0);
        freeBoard2.setCommentCount(0);
        freeBoard2.setFilePath(null);
        freeBoard2.setCreatedBy(members2.getNickname());
        freeBoard2.setModifiedBy(members2.getNickname());

        freeBoardRepository.save(freeBoard2);

        List<FreeBoard> listPosts = freeBoardRepository.findAll();
        Assertions.assertThat(listPosts).isNotNull();
        Assertions.assertThat(listPosts.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("자유게시글 수정")
    public void testUpdatedPost() {
        // given(준비)
        FreeBoard originalPost = freeBoardRepository.save(freeBoard);
        // when(실행)
        originalPost.setTitle("updated test title");
        originalPost.setContent("I am updating the content");
        // then(검증)
        FreeBoard updatedPost = freeBoardRepository.findByFreeBoardId(originalPost.getFreeBoardId());
        Assertions.assertThat(updatedPost).isNotNull();
        Assertions.assertThat(updatedPost.getTitle()).isEqualTo("updated test title");
        Assertions.assertThat(updatedPost.getContent()).isEqualTo("I am updating the content");
        Assertions.assertThat(updatedPost.getNickname()).isEqualTo("testy");
    }

    @Test
    @DisplayName("자유게시글 삭제")
    public void testDeletePost() {
        // given(준비)
        FreeBoard savedPost = freeBoardRepository.save(freeBoard);
        // when(실행)
        freeBoardRepository.deleteByFreeBoardId(savedPost.getFreeBoardId());
        // then(검증)
        FreeBoard result = freeBoardRepository.findByFreeBoardId(savedPost.getFreeBoardId());
        Assertions.assertThat(result).isNull();
    }

    @Test
    @DisplayName("자유게시글 조회_1.게시글 번호")
    public void testFindById() {
        FreeBoard foundPost = freeBoardRepository.findByFreeBoardId(freeBoard.getFreeBoardId());
        Assertions.assertThat(foundPost).isNotNull();
        Assertions.assertThat(foundPost.getTitle()).isEqualTo("test title");
        Assertions.assertThat(foundPost.getNickname()).isEqualTo("testy");
    }

}



// deleteByFreeBoardId 테스트

// 게시글 번호, 제목, 작성자로 조회 테스트
//        FreeBoard foundPost = freeBoardRepository.findByFreeBoardId(members.getUserId());
//        Assertions.assertThat(foundPost).isNotNull();
//        Assertions.assertThat(foundPost.getFreeBoardId()).isEqualTo(1);
//        Assertions.assertThat(foundPost.getTitle()).isEqualTo("test title");
//        Assertions.assertThat(foundPost.getNickname()).isEqualTo("testy");

// Question) 자동으로 작성자 추가 하는 거 어떻게 구현해야하지? -> Auditing도 연관되어 有