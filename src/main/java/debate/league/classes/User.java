package debate.league.classes;

public class User{
    private int user_id;
    private String name;
    private String username;
    private String password;
    private String role;

    public User(int user_id, String name, String username, String password, String role){
        this.user_id = user_id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getUserId(){
        return this.user_id;
    }

    public void setUserId(int id){
        this.user_id = id;
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