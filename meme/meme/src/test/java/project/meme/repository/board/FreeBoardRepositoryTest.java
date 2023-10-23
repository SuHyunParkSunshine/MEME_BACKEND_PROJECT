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
import project.meme.domain.board.FreeBoard;
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

    @Test
    @DisplayName("게시글 등록 테스트")
    public void testCreatePosts() {
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

        FreeBoard freeBoard = new FreeBoard();
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

        //findAll 테스트
//        List<FreeBoard> findAllPosts = freeBoardRepository.findAll();
//        Assertions.assertThat(findAllPosts).isNotNull();

        // deleteByFreeBoardId 테스트

        // 게시글 번호, 제목, 작성자로 조회 테스트
        FreeBoard foundPost = freeBoardRepository.findByFreeBoardId(members.getUserId());
        Assertions.assertThat(foundPost).isNotNull();
        Assertions.assertThat(foundPost.getFreeBoardId()).isEqualTo(1);
        Assertions.assertThat(foundPost.getTitle()).isEqualTo("test title");
        Assertions.assertThat(foundPost.getNickname()).isEqualTo("testy");
    }
}


// Question) 자동으로 작성자 추가 하는 거 어떻게 구현해야하지?