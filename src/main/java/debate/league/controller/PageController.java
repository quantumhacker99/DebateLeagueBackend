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
import debate.league.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@RestController
@CrossOrigin("http://localhost:3000")

public class PageController {


    private PostService postService;
    private UserService userService;

    public PageController(UserService userService, PostService postService){
        this.userService = userService;
        this.postService = postService;
        
    }
    
    @RequestMapping(value="/postProfile/{postId}", method=RequestMethod.GET)
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

            nwPost.setUpvotes(post.get().getUpvotes());
            nwPost.setDownvotes(post.get().getDownvotes());

            nwPost.setParent(post.get().getParent());
            nwPost.setChild(post.get().getChild());

            nwPost.setUser(post.get().getUser().getUserId());
            nwPost.setReplyUser(post.get().getReplyUserId());

            System.out.println("GET Request made " + nwPost.getPostId() + " " + nwPost.getBody() + " " + 
                                nwPost.getUpvotes() + " " + nwPost.getDownvotes() + " " + 
                                nwPost.getParent() + " " + nwPost.getChild() + " " +
                                nwPost.getUser() + nwPost.getReplyUser());
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

    @RequestMapping(value="/postProfile/{postId}", method=RequestMethod.POST)
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

            localPostUpdate.setUpvotes(updatedPost.getUpvotes());
            localPostUpdate.setDownvotes(updatedPost.getDownvotes());

            //Parent won't change in an update anyways, so not setting here. User also will not change. 

            localPostUpdate.setChild((long)updatedPost.getChild());

            postService.savePost(localPostUpdate);

            System.out.println("Updated Post Body: " + localPostUpdate.getBody() + " " + localPostUpdate.getUpvotes()
                                + " " + localPostUpdate.getDownvotes() + " " + localPostUpdate.getChild());
            
            response.setSuccess(true);
            response.setChild(localPostUpdate.getChild());
        }
        
        return response;
    }

    @RequestMapping(value="/createPost", method=RequestMethod.POST)
    @CrossOrigin
    public @ResponseBody SendConfirmationPostDTO createNewPost(@RequestBody PostUsersDTO users){

        SendConfirmationPostDTO response = new SendConfirmationPostDTO();
        
        Long userId = Long.parseLong(users.getUserId());
        Long replyUserId = Long.parseLong(users.getReplyUserId());

        Post newPost = new Post();
        newPost.setParent((long)-1);
        newPost.setUser(this.userService.getUserById(userId).get());
        newPost.setReplyUserId(replyUserId);
        //newPost.setReplyUser(this.userService.getUserById(replyUserId).get());

        //Optional<Post> parentPost = this.postService.getPostById(parentId);
        //Post localParentPost = parentPost.get();
        postService.savePost(newPost);

        response.setSuccess(true);
        response.setChild(replyUserId);
        
        return response;
    }

    @RequestMapping(value="/createPost/{parent}", method=RequestMethod.POST)
    @CrossOrigin
    public @ResponseBody SendConfirmationPostDTO createReplyPost( @PathVariable String parent){

        SendConfirmationPostDTO response = new SendConfirmationPostDTO();
        Long parentId = Long.parseLong(parent);
        Long replyUserId = this.postService.getPostById(parentId).get().getReplyUserId();
        //Long.parseLong(replyUser);

        Post newPost = new Post(parentId);
        newPost.setParent(parentId);

        newPost.setUser(this.userService.getUserById(replyUserId).get());
        newPost.setReplyUserId(parentId);
        //newPost.setReplyUser(this.userService.getUserById(parentId).get());

        Optional<Post> parentPost = this.postService.getPostById(parentId);
        Post localParentPost = parentPost.get();

        if(parentPost.isEmpty() || localParentPost.getPostId() != parentId){
            response.setSuccess(false);
        }
        else{
            postService.savePost(newPost);

            response.setSuccess(true);
            response.setChild(newPost.getPostId());
            response.setUserId(replyUserId);
            response.setReplyId(parentId);

            localParentPost.setChild(newPost.getPostId());

            postService.savePost(localParentPost);
        }
        return response;
    }

    @RequestMapping(value="/createNewPost/{user}", method=RequestMethod.POST)
    @CrossOrigin
    public @ResponseBody SendConfirmationPostDTO createNewPost(@PathVariable String user){
        Long parentId = (long)-1;
        Long userId = Long.parseLong(user);
        Long replyUserId = (long)-1;
        //Long.parseLong(replyUser);

        Post newPost = new Post();
        newPost.setUser(this.userService.getUserById(userId).get());
        newPost.setParent(parentId);
        newPost.setBody("");
        newPost.setChild((long)-1);
        newPost.setReplyUserId(replyUserId);
        //newPost.setReplyUser(this.userService.getUserById(parentId).get());

        postService.savePost(newPost);

        SendConfirmationPostDTO response = new SendConfirmationPostDTO();
        response.setSuccess(true);
        response.setChild(newPost.getPostId());
        response.setUserId(userId);
        response.setReplyId(replyUserId);
        return response;
    }

    @RequestMapping(value="/fetchPosts", method=RequestMethod.GET)
    @CrossOrigin
    public @ResponseBody List<PostDTO> fetchHeadPostDetails(){
        List<PostDTO> headPostDTOs= new ArrayList<PostDTO>();
        List<Post> headPosts = this.postService.getAllHeadPosts();

        for(Post post:headPosts){
            PostDTO nwPostDTO = new PostDTO();
            nwPostDTO.setNull(false);
            nwPostDTO.setPostId(post.getPostId());
            nwPostDTO.setBody(post.getBody());
            nwPostDTO.setParent(post.getParent());
            nwPostDTO.setChild(post.getChild());
            nwPostDTO.setUpvotes(post.getUpvotes());
            nwPostDTO.setDownvotes(post.getDownvotes());
            nwPostDTO.setUser(post.getUser().getUserId());
            nwPostDTO.setReplyUser(post.getReplyUserId());

            headPostDTOs.add(nwPostDTO);
        }

        return headPostDTOs;
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
    private Integer upvotes;
    private Integer downvotes;
    private Long parent;
    private Long child;
    private Long user;
    private Long replyUser;
}

@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class PostUsersDTO{
    private String userId;
    private String replyUserId;
}

@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class SendConfirmationPostDTO{
    private boolean success;
    private Long child;

    private Long userId;
    private Long replyId;
}