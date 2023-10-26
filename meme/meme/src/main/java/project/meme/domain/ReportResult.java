package project.meme.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "processDate")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "adminNickname"))
})
public class ReportResult extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportResultId;

    private String processResult; // 처리내용

    protected ReportResult() {}

    private ReportResult(String processResult) {
        this.processResult = processResult;
    }
}
