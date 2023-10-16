package project.meme.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.meme.dto.MembersDto;
import project.meme.service.MembersService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MembersController {

//    @Autowired
//    MembersService membersService;

    private final MembersService membersService;

    // Logger 인스턴스 생성
    private static final Logger LOGGER = LoggerFactory.getLogger(MembersController.class);

    //일반사용자 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> registerController(@RequestBody @Valid MembersDto membersDto, BindingResult bindingResult) {
        // Vaild : 유효성 검사 대상이 하나인 경우 사용
        if(bindingResult.hasErrors()) {
            //유효성 검사 오류가 발생한 경우
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            String errorMessage = fieldErrors.get(0).getDefaultMessage();

            // 로그로 오류 메세지 기록
            LOGGER.error(errorMessage);

            return ResponseEntity.badRequest().body(errorMessage);
        }
        return membersService.registerService(membersDto);
    }

    //관리자 회원가입 // URL주소를 admin을 앞으로 해야 나중에 권한 설정 config할 때 편하게 할 수 있음
    @PostMapping("/admin/register")
    public ResponseEntity<String> adminRegisterController(@RequestBody MembersDto membersDto) {
        return membersService.adminRegisterService(membersDto);
    }
}
