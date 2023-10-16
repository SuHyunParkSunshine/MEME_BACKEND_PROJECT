package project.meme.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.group.GroupSequenceProvider;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MembersDto {

    @NotNull(message = "이름을 입력해 주세요.")
    private String userName;

    @NotNull(message = "이메일을 입력해 주세요.")
    private String email;

    @NotNull(message = "닉네임을 입력해 주세요.")
    private String nickname;

    @NotNull(message = "비밀번호를 입력해 주세요.")
    private String password;

    @NotNull(message = "비밀번호를 확인해 주세요.")
    private String confirmPassword;

    @NotNull(message = "전화번호를 입력해주세요.")
    private String phoneNumber;

    private String userAddress;
}
