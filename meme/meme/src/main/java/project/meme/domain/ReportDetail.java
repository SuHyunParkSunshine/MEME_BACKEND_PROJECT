package project.meme.domain;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@AttributeOverride(name = "createdAt", column = @Column(name = "reportDate"))
public class ReportDetail extends AuditingFields{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩 사용
    @JoinColumn(name="user_id")
    private Members member; // ROLE_MEMBER

    private String reporterNickname; // 생성자(신고한사람) >> 상속
    private String reporteeNickname; // 신고당한 사람
    private String reportUrl;        // 해당하는 URL
    private String reportContents;   // 신고 상세내용
    private String filePath;         // 첨부파일

    protected ReportDetail() { } // Entity 생성을 위한 생성자

    private ReportDetail(Members member, String reporterNickname, String reporteeNickName, String reportUrl, String reportContents, String filePath) {
        this.member = member;
        this.reporterNickname = reporterNickname;
        this.reporteeNickname = reporteeNickName;
        this.reportUrl = reportUrl;
        this.reportContents = reportContents;
        this.filePath = filePath;
    }
}
