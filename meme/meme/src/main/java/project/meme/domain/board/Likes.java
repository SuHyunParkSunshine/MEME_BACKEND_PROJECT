package project.meme.domain.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import project.meme.domain.Members;

@Getter
@ToString
@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @Setter
    @JoinColumn(name = "userId")
    @ManyToOne(optional = false) // 다대원 방식
    private Members members;

    @Setter
    @JoinColumn(name = "freeBoardId")
    @ManyToOne
    private FreeBoard freeBoard;

    @Setter
    @JoinColumn(name = "noticeBoardId")
    @ManyToOne
    private NoticeBoard noticeBoard;
}
