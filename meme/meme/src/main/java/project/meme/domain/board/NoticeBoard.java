package project.meme.domain.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import project.meme.domain.AuditingFields;
import project.meme.domain.Members;

@Getter
@ToString(callSuper = true)
@Entity
public class NoticeBoard extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeBoardId;

    @Setter
    @JoinColumn(name = "userId")
    @ManyToOne(optional = false)
    private Members members;

    @Setter
    @Column(nullable = false)
    private String nickname;

    @Setter
    @Column(nullable = false)
    private String title;
    @Setter
    @Column(nullable = false)
    private String content;

    @Setter
    @Column(nullable = false)
    private int likeCount;
    @Setter
    @Column(nullable = false)
    private int viewCount;
    @Setter
    @Column(nullable = false)
    private int commentCount;

    //선택
    @Setter
    private String filePath;
}
