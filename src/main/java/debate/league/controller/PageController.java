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
import debate.league.repositories.PostRepository;
import debate.league.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@RestController
@CrossOrigin("http://localhost:3000")

public class PageController {


    private PostService postService;

    public PageController(PostService postService){
        this.postService = postService;
        
    }
    
    @RequestMapping(value="/api/postProfile/{postId}", method=RequestMethod.GET)
    @CrossOrigin
    public @ResponseBody PostDTO fetchPostDetails(@PathVariable String postId){

      
        PostDTO nwPost = new PostDTO();
        Long reqPostId = Long.parseLong(postId);
        Optional<Post> post = this.postService.getPostById(reqPostId);

        if(post.isEmpty()){
            nwPost.setNull(true);
            System.out.println(nwPost.getNull());
        }

        else {
            nwPost.setNull(false);
            nwPost.setPostId(post.get().getPostId());
            nwPost.setBody(post.get().getBody());
            nwPost.setChild(post.get().getChild());
            System.out.println("GET Request made " + nwPost.getPostId() + " " + nwPost.getBody() + " "  + " " + nwPost.getChild());
        }


        return nwPost;
        /*
        nwPost.setPostId(Long.parseLong(postId));
        nwPost.setBody("Hello World " + postId);
        nwPost.setUpvotes(0);
        nwPost.setDownvotes(0);
        nwPost.setChild(Integer.parseInt(postId)+1);
        return nwPost;*/
    }

    @RequestMapping(value="/api/postProfile/{postId}", method=RequestMethod.POST)
    @CrossOrigin
    public @ResponseBody SendConfirmationPostDTO submitPost(@RequestBody PostDTO updatedPost, @PathVariable String postId){

        SendConfirmationPostDTO response = new SendConfirmationPostDTO();
        Long reqPostId = Long.parseLong(postId);
        Optional<Post> localPost = this.postService.getPostById(reqPostId);
        

        if(localPost.isEmpty() || localPost.get().getPostId() != updatedPost.getPostId()){
            response.setSuccess(false);
        }

        else{
            Post localPostUpdate = localPost.get();
            
            localPostUpdate.setBody(updatedPost.getBody());
            localPostUpdate.setChild(updatedPost.getChild());

            postService.savePost(localPostUpdate);

            
            
            response.setSuccess(true);
        }
        
        return response;
    }
}


// helper DTO
@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class PostDTO{
    public boolean getNull() {
        return isNull;
    }
    private boolean isNull;
    private Long postId;
    private String body;
    private String title;
    private String tags;
    private Long child;
    private Long parent;
}

@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class SendConfirmationPostDTO{
    private boolean success;
}