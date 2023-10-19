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
public class CommentAdministration extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentAdminId;

    @Setter
    @JoinColumn(name = "userId")
    @ManyToOne(optional = false) // 'optional = false' 무조건 참조 요
    private Members members;

    @Setter
    @JoinColumn(name = "freeBoardId")
    @ManyToOne
    private FreeBoard freeBoard;

    @Setter
    @JoinColumn(name = "noticeBoardId")
    @ManyToOne
    private NoticeBoard noticeBoard;

    @Setter
    @Column(nullable = false)
    private String commentContent;

    @Setter
    @Column(nullable = false)
    private String nickname;

}
