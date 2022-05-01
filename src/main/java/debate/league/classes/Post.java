package debate.league.classes;

import javax.annotation.Generated;
import javax.persistence.*;

import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="post_id")
    private Long post_id;
    private String body;
    private Long child;
    private Long parent;

    private String tags;
    private String title;

    public Post(Long post_id, Long child, Long parent, String tags, String title){
        this.post_id = post_id;
        this.body = "";
        this.child = child;
        this.parent = parent;
        this.tags = tags;
        this.title = title;
    }

    @ManyToOne(targetEntity = User.class)
    private User user;

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

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTags(){
        return this.tags;
    }

    public void setTags(String tags){
        this.title = tags;
    }

    public Long getChild(){return this.child;}

    public Long getParent(){return this.parent;}

    public void setChild(Long child){this.child=child;}

    public void setParent(Long parent){this.parent=parent;}

    public User getUser(){return this.user;}

    public void setUser(User user){this.user = user;}


}