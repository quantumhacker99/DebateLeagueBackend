package debate.league.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;
    private String name;
    //private String username;
    //private String password;
    //private String role;

    public User(Long user_id, String name){//, String username, String password, String role){
        this.user_id = user_id;
        this.name = name;
        /*this.username = username;
        this.password = password;
        this.role = role;*/
    }

    @OneToOne(targetEntity = LoginDetails.class, mappedBy = "user")
    private LoginDetails loginDet;

    @OneToMany(targetEntity = Post.class, mappedBy = "user")
    private List<Post> posts;

    public Long getUserId(){
        return this.user_id;
    }

    public void setUserId(Long id){
        this.user_id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    /*public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getRole(){
        return this.role;
    }

    public void setRole(String role){
        this.role = role;
    }*/
    
}