package project.meme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.meme.domain.Members;
import project.meme.dto.MembersDto;
import project.meme.repository.MembersRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class MembersService {

//    @Autowired
//    MembersRepository membersRepository;

    private final MembersRepository membersRepository;
    private final BCryptPasswordEncoder encoder;

    // 일반사용자 회원가입
    public ResponseEntity<String> registerService(MembersDto membersDto) {
        if (membersRepository.findByEmail(membersDto.getEmail()) == null) {
            if (!membersDto.getPassword().equals(membersDto.getConfirmPassword())) {
                System.out.println("UserName : " + membersDto.getUserName());
                System.out.println("Password : " + membersDto.getPassword());
                System.out.println("ConfirmPassword : " + membersDto.getConfirmPassword());
                return ResponseEntity.ok("비밀번호가 일치하지 않습니다");
            } else {
                Members members = new Members();
                members.setUserName(membersDto.getUserName());
                members.setEmail(membersDto.getEmail());
                members.setNickname(membersDto.getNickname());
                members.setPassword(encoder.encode(membersDto.getPassword()));
                members.setConfirmPassword(encoder.encode(membersDto.getConfirmPassword()));
                members.setPhoneNumber(membersDto.getPhoneNumber());
                members.setUserAddress(membersDto.getUserAddress());
                members.setRole("ROLE_USER");
                members.setCreationDate(new Date());
                members.setStatus("active");
                members.setReportingCount(0);

                membersRepository.save(members);

                return ResponseEntity.ok("회원가입이 완료되었습니다");
            }
        }
        return ResponseEntity.ok("이미 존재하는 이메일입니다.");
    }

    //관리자 회원가입
    public ResponseEntity<String> adminRegisterService(MembersDto membersDto) {

        if(membersRepository.findByEmail(membersDto.getEmail()) == null) {
            if(!membersDto.getPassword().equals(membersDto.getConfirmPassword())) {
                return ResponseEntity.ok("비밀번호가 일치하지 않습니다.");
            } else {
                Members adminMembers = new Members();
                adminMembers.setUserName(membersDto.getUserName());
                adminMembers.setEmail(membersDto.getEmail());
                adminMembers.setNickname(membersDto.getNickname());
                adminMembers.setPassword(encoder.encode(membersDto.getPassword()));
                adminMembers.setConfirmPassword(encoder.encode(membersDto.getConfirmPassword()));
                adminMembers.setPhoneNumber(membersDto.getPhoneNumber());
                adminMembers.setUserAddress(membersDto.getUserAddress());
                adminMembers.setRole("ROLE_ADMIN");
                adminMembers.setCreationDate(new Date());
                adminMembers.setStatus("active");
                adminMembers.setReportingCount(0);

                membersRepository.save(adminMembers);

                return ResponseEntity.ok("관리자가 되신 걸 축하합니다:)");
            }
        }
        return ResponseEntity.ok("이미 존재하는 관리자 이메일입니다");
    }
}
