package debate.league.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import debate.league.classes.Post;
import debate.league.repositories.PostRepository;
import lombok.NonNull;

@Service
public class PostService {

    @NonNull
    private final PostRepository postRepository;

    @Autowired
    PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long postId){
        return postRepository.findById(postId);
    }    

    public void savePost(Post post){
        postRepository.save(post);
    }

    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }


}
