package project.meme.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@AttributeOverride(name = "createdAt", column = @Column(name = "approvalDate"))
public class ReportApproval extends AuditingFields{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long approvalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Members admin; // ROLE_ADMIN

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private ReportDetail reportDetail; // 신고내역

    private Boolean approval; // 승인여부

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reportResultId")
    private ReportResult reportResultId; // 신고처리결과

    protected ReportApproval() {}

    private ReportApproval(Members admin, ReportDetail reportDetail, Boolean approval, ReportResult reportResultId) {
        this.admin = admin;
        this.reportDetail = reportDetail;
        this.approval = approval;
        this.reportResultId = reportResultId;
    }
}
