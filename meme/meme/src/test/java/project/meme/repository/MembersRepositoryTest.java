package project.meme.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import project.meme.domain.Members;

import java.util.Date;

@DataJpaTest // >> 인메모리 데이터베이스에 저장
@ActiveProfiles("test") // >> application-test.properties를 이용, 테스트케이스용 설정 파일 생성
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // 테스트 메서드 생성 후 컨테스트 정리
public class MembersRepositoryTest {

    @Autowired
    private MembersRepository membersRepository;

    @Test
    @DisplayName("회원등록 테스트")
    public void testRegisterMembers() {
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


        // findByEmail 테스트
        Members foundMember = membersRepository.findByEmail("test12@gmail.com");

        // 조회된 회원과 예상 결과를 비교
        Assertions.assertThat(foundMember).isNotNull();
        Assertions.assertThat(foundMember.getEmail()).isEqualTo("test12@gmail.com");
        Assertions.assertThat(foundMember.getUserName()).isEqualTo("test");
    }
}
/*
 * @DataJpaTest 기능
 * - JPA 관련된 설정만 로드한다. (WebMVC와 관련된 Bean이나 기능은 로드되지 않는다)
 * - JPA를 사용해서 생성/조회/수정/삭제 기능의 테스트가 가능하다.
 * - @Transactional을 기본적으로 내장하고 있으므로, 매 테스트 코드가 종료되면 자동으로 DB가 롤백된다.
 * - 기본적으로 내장 DB를 사용하는데, 설정을 통해 실제 DB로 테스트도 가능하다.
 * - @Entity가 선언된 클래스를 스캔하여 저장소를 구성한다.
 */