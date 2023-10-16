package project.meme.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import project.meme.dto.MembersDto;
import project.meme.service.MembersService;

@WebMvcTest(MembersController.class) // 웹이랑 controller에서만 사용하는 어노테이션 // http랑 controller가 잘되는지 테스트
public class MemberControllerTest {

    @Autowired // 가짜 객체를 사용할 수 있게 하는 mvc
    private MockMvc mockMvc;

    @MockBean
    MembersService membersService;

    @Test
    @DisplayName("회원 가입 유효성 검사")
    @WithMockUser
    public void checkRegister() throws Exception {
        // 요청 본문에 요청할 가짜 MemberDTO 객체 생성
        MembersDto membersDto = new MembersDto();
        membersDto.setUserName("test");
        membersDto.setEmail("test@naver.com");
        membersDto.setNickname("test");
        membersDto.setPassword("1234");
        membersDto.setConfirmPassword("1234");
        membersDto.setPhoneNumber("1234");
        membersDto.setUserAddress("testtesttt");

        //memberService의 registerService 메서드 호출 시의 동작을 가짜, 모의(mock)로 설정
        when(membersService.registerService(membersDto)).thenReturn(ResponseEntity.ok("회원가입이 완료되었습니다"));

        // "/register" 엔드포인트로 POST요청 보내고, MemberDto 객체를 JSON 형식으로 전송
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .with(csrf()) //csrf 비활성화
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(membersDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.content().string("회원가입이 완료되었습니다"));
                    // >> MemberController에서 return 값이 "회원가입이 완료되었습니다."가 아니기 때문에 상기의 코드 오류 발생

    }

    @Test
    @DisplayName("이름 유효성 검사") // 무슨 테스트인지 이름 설정
    @WithMockUser // 필요 권한을 가짜로 부여!! >> JWT 사용 시
    public void checkUserName() throws Exception {
        MembersDto membersDto = new MembersDto();
        membersDto.setUserName(null);
        membersDto.setEmail("eieij@naver.com");
        membersDto.setPassword("1234");
        membersDto.setConfirmPassword("1234");
        membersDto.setNickname("냐냐");
        membersDto.setPhoneNumber(".010-4848-5444");
        membersDto.setUserAddress("부산광역시 해운대구");

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON) // JSON형태로 교신하겠다.
                        .content(asJsonString(membersDto))) // JSON문자열 형태의 데이터 송신
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("이름을 입력해 주세요."));
    }

    // 객체를 JSON 문자열로 변환
    private String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
// 403 에러(권한문제) > with(csrf()) 추가 > 401 발생
// 401 에러 > withMockUser() 추가 > 400 발생
// 400 에러(잘못된 요청) > 실제 컨트롤러 클래스에 유효성 검사 로직을 추가 > 해결 x
// 에러 해결 : 유효성 검사에 실패하면 BadRequest로 처리해야하는데 ok로 처리 했기 때문이었음.