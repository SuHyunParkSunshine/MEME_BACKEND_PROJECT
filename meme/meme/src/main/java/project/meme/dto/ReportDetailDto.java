package project.meme.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDetailDto {
    private String reporterNickname;
    private String reporteeNickname;
    private String reportUrl;
    private String reportContents;
    private String filePath;

    public ReportDetailDto(String reporterNickname, String reporteeNickname, String reportUrl, String reportContents, String filePath) {
        this.reporterNickname = reporterNickname;
        this.reporteeNickname = reporteeNickname;
        this.reportUrl = reportUrl;
        this.reportContents = reportContents;
        this.filePath = filePath;
    }
}
