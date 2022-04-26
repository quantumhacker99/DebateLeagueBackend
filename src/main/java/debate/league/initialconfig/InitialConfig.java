package debate.league.initialconfig;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import debate.league.classes.User;
import debate.league.classes.Post;
import debate.league.repositories.PostRepository;
import debate.league.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class InitialConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, PostRepository postRepository){
        return args -> {

            User user1 = new User((long) 1, "hemanth", "hem123", "hemc", "debater");
            User user2 = new User((long) 2, "harshvardhan", "harsh456", "harsh", "debater");
            User user3 = new User((long) 3, "ameya", "ameya789","ameya", "debater");

            Post post1 = new Post((long)1, 2, user1.getUserId(), user2.getUserId());
            Post post2 = new Post((long)2, 2, user1.getUserId(), user2.getUserId());
            Post post3 = new Post((long)3, 4, user2.getUserId(), user3.getUserId());
            Post post4 = new Post((long)4, 4, user2.getUserId(), user3.getUserId());
    
            userRepository.saveAllAndFlush(List.of(user1, user2, user3)); 
            postRepository.saveAllAndFlush(List.of(post1, post2, post3, post4));
        };
    }

    public static void main(String[] args) {
        
    }
    
}

