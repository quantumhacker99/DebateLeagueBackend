package debate.league.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String name;
    private String username;
    private String password;
    private String role;

    public User(Long userId, String name, String username, String password, String role){
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @OneToMany(targetEntity = Post.class)

    public Long getUserId(){
        return this.userId;
    }

    public void setUserId(Long id){
        this.userId = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUsername(){
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
    }
    
}