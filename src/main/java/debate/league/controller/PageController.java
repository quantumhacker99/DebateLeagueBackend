package debate.league.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import debate.league.classes.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@RestController
@CrossOrigin("http://localhost:3000")

public class PageController {


    public PageController(){
        
    }
    
    @RequestMapping(value="/postProfile/{postId}", method=RequestMethod.GET)
    @CrossOrigin
    public @ResponseBody PostDTO fetchPostDetails(@PathVariable String postId){

      
        PostDTO nwPost = new PostDTO();

        nwPost.setPostId(Integer.parseInt(postId));
        nwPost.setBody("Hello World " + postId);
        nwPost.setUpvotes(0);
        nwPost.setDownvotes(0);
        nwPost.setChild(0);

        return nwPost;
    }
}


// helper DTO
@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class PostDTO{
    private Integer postId;
    private String body;
    private Integer upvotes;
    private Integer downvotes;
    private Integer child;
}