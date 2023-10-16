package project.meme.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project.meme.domain.Members;
import project.meme.dto.MembersDto;
import project.meme.repository.MembersRepository;

@ExtendWith(MockitoExtension.class) // Mockito 초기화
public class MembersServiceTest {

    @Mock
    private MembersRepository membersRepository;

    @Mock
    private BCryptPasswordEncoder encoder;

    @Test
    @DisplayName("회원가입 테스트")
    public void checkRegisterService() {
        // 가짜 MembersDto 객체 생성
        MembersDto fakeMemberDto = new MembersDto();
        fakeMemberDto.setUserName("test");
        fakeMemberDto.setEmail("test122@gmail.com");
        fakeMemberDto.setPassword("1234");
        fakeMemberDto.setConfirmPassword("1234");
        fakeMemberDto.setNickname("testy");
        fakeMemberDto.setPhoneNumber("010-1234-5678");
        fakeMemberDto.setUserAddress("벡스코역 3번 출구");

        // 가짜 회원 생성 및 반환 설정 >> 위에서 만든 mock 객체가 잘 작동하는지 확인 하는 코드라 회원가입 테스트와는 상관 없음
        Members fakeMember = new Members();

        Mockito.when(encoder.encode(fakeMemberDto.getPassword())).thenReturn("encodedPassword");
        Mockito.when(membersRepository.save(Mockito.any())).thenReturn(fakeMember);

        // MemberService 객체 생성 >> Mock으로 만든 memberRepo랑 encoder 가지고 테스트 해달로 의존성 주입
        MembersService membersService = new MembersService(membersRepository, encoder);

        // registerService method 호출
        ResponseEntity<String> responseEntity = membersService.registerService(fakeMemberDto);

        //결과 검증
        Assertions.assertEquals("회원가입이 완료되었습니다", responseEntity.getBody());
    }

    @Test
    @DisplayName("비밀번호와 확인비밀번호 불일치 테스트")
    public void checkMismatchedPassword() {

        // 가짜 MembersDto 객체 생성
        MembersDto fakeMemberDto = new MembersDto();
        // 테스트 시나리오에서 요구되는 값만 setting 가능, setting을 따로 하지 않을 경우 각 타입의 초기값으로 설정 됨 ex. string -> null
        fakeMemberDto.setPassword("1234");
        fakeMemberDto.setConfirmPassword("12341234");

        // MemberService 객체 생성
        MembersService membersService = new MembersService(membersRepository, encoder);

        // registerService method 호출
        ResponseEntity<String> responseEntity = membersService.registerService(fakeMemberDto);

        Assertions.assertEquals("비밀번호가 일치하지 않습니다", responseEntity.getBody());
    }

    @Test
    @DisplayName("이미 존재하는 이메일 테스트")
    public void checkExistedEmail() {

        // 가짜 MembersDto 객체 생성
        MembersDto fakeMemberDto = new MembersDto();
        fakeMemberDto.setEmail("test12@gmail.com");

        // 이미 존재하는 이메일로 회원 조회 시도 후 가짜 회원을 반환
        Mockito.when(membersRepository.findByEmail(fakeMemberDto.getEmail())).thenReturn(new Members());

        // MemberService 객체 생성
        MembersService membersService = new MembersService(membersRepository, encoder);

        //registerService method 호출
        ResponseEntity<String> responseEntity = membersService.registerService(fakeMemberDto);

        Assertions.assertEquals("이미 존재하는 이메일입니다.", responseEntity.getBody());

    }
}

/*
 * @ExtendWith란?
 * SpringBoot와 무관한 Mockito에서 제공하는 단위 테스트 어노테이션
 * 서비스와 같은 개별 컴포넌트를 테스트할 때 사용
 *
 * @ExtendWith(MockitoExtension.class) 사용이유
 * Mockito와 JUnit5와 통합하여 테스트를 가능하게 한다.
 *
 * JUnit5 : 기본적인 테스트용 프레임워크
 * Mockito : JUnit5의 기능을 확장 시키는?? 느낌의 프레임 워크
 *
 * Mockito를 이용하면 의존성을 쉽게 해결할 수 있다.(가짜객체생성!)
 */
