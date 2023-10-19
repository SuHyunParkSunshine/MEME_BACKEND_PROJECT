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
public class FreeBoard extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long freeBoardId;

    @Setter
    @JoinColumn(name = "userId")
    @ManyToOne(optional = false) // 'optional = false' 반드시 관계가 존재 해야 한다
    private Members members;

    @Setter
    @Column(nullable = false)
    private String nickname; // 게시글 닉네임

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

    // 선택
    @Setter
    private String filePath;

}
