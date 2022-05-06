package debate.league.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import debate.league.classes.Post;
import debate.league.repositories.PostRepository;
import lombok.NonNull;

import java.util.stream.Collectors;

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

    public List<Post> getAllHeadPosts(){
        return postRepository.findAll().stream().filter(post -> post.getParent() == -1).collect(Collectors.toList());
        
    }

    public Optional<Post> getPostById(Long postId){
        return postRepository.findById(postId);
    }    

    public void savePost(Post post){
        postRepository.saveAndFlush(post);
    }

    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }


}
