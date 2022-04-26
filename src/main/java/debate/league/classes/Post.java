package debate.league.classes;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long post_id;
    private String body;
    private int upvotes;
    private int downvotes;
    private int child;
    private Long firstUserId;
    private Long secondUserId;

    public Post(Long post_id, int child, Long user1, Long user2){
        this.post_id = post_id;
        this.body = "";
        this.upvotes = 0;
        this.downvotes = 0;
        this.child = child;
        this.firstUserId = user1;
        this.secondUserId = user2;
    }

    @ManyToOne(targetEntity = User.class)

    public Long getPostId(){
        return this.post_id;
    }

    public void setPostId(Long post_id){
        this.post_id = post_id;
    }

    public String getBody(){
        return this.body;
    }

    public void setBody(String body){
        this.body = body;
    }

    public int getUpvotes(){
        return this.upvotes;
    }

    public void setUpvotes(int upvotes){
        this.upvotes = upvotes;
    }

    public int getDownvotes(){
        return this.downvotes;
    }

    public void setDownvotes(int downvotes){
        this.downvotes = downvotes;
    }  
    
    public int getChild(){
        return this.child;
    }

    public void setChild(int child){
        this.child = child;
    }

    public Long getFirstUserId(){
        return this.firstUserId;
    }

    public void setFirstUser(Long firstUser){
        this.firstUserId = firstUser;
    }

    public Long getSecondUserId(){
        return this.secondUserId;
    }

    public void setSecondUser(Long secondUser){
        this.secondUserId = secondUser;
    }

}