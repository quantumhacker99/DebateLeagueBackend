package debate.league.classes;

import javax.annotation.Generated;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "post")
public class Post{
    @SequenceGenerator(
		name = "post_sequence",
		sequenceName = "post_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "post_sequence"
	)

    @Id
    private Long postId;
    private String body;
    private Long child;
    private Long parent;
    private int upvotes;
    private int downvotes;

    private String tags;
    private String title;

    public Post(Long child, Long parent, String tags, String title){
        //this.postId = post_id;
        this.body = "";
        this.child = child;
        this.parent = parent;
        this.tags = tags;
        this.title = title;
        this.upvotes = 0;
        this.downvotes = 0;
    }

    public Post(Long parent){
        this.parent = parent;
        this.child = (long)-1;

        this.tags = "";
        this.title = "";
        this.body = "";
        this.upvotes = 0;
        this.downvotes = 0;
    }

    @OneToOne(targetEntity = User.class)
    private User user;

    private Long replyUserId;
}