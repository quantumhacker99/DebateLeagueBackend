package debate.league.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "user")
public class User{

    @SequenceGenerator(
		name = "user_sequence",
		sequenceName = "user_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "user_sequence"
	)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long userId;
    private String name;
    // private String username;
    // private String password;
    // private String role;

    public User(String name){//, String username, String password, String role){
        //this.userId = userId;
        this.name = name;
        // this.username = username;
        // this.password = password;
        // this.role = role;
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

    // public String getUsername(){
    //     return this.username;
    // }

    // public void setUsername(String username){
    //     this.username = username;
    // }

    // public String getPassword(){
    //     return this.password;
    // }

    // public void setPassword(String password){
    //     this.password = password;
    // }

    // public String getRole(){
    //     return this.role;
    // }

    // public void setRole(String role){
    //     this.role = role;
    // }
    
}