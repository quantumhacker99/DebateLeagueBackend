package debate.league.classes;
import javax.annotation.Generated;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "invitation")
public class Invitation {
    @SequenceGenerator(
		name = "invite_sequence",
		sequenceName = "invite_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "invite_sequence"
	)

    @Id
    private Long invitationID;

    private String topic;
    private String body;
    private Long postId;
    private Long RecipientId;
    private Long ReceivedFrom;

    @ManyToOne(targetEntity = User.class)
    private User recipientUser;

    @OneToOne(targetEntity = Post.class)
    private Post post;
    
}
