package debate.league.classes;

public class Post{

    private int post_id;
    private String body;
    private int upvotes;
    private int downvotes;
    private int child;

    public Post(int post_id){
        this.post_id = post_id;
        this.body = "";
        this.upvotes = 0;
        this.downvotes = 0;
        this.child = -1;
    }

    public int getPostId(){
        return this.post_id;
    }

    public void setPostId(int post_id){
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

}