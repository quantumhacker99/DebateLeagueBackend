package debate.league.initialconfig;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import debate.league.classes.User;
//import debate.league.classes.LoginDetails;
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

            User user1 = new User("hemanth");//, "hem123", "hemc", "debater");
            User user2 = new User("harshvardhan");//, "harsh456", "harsh", "debater");
            User user3 = new User("ameya");//, "ameya789","ameya", "debater");

            // LoginDetails login1 = new LoginDetails("hem123", "hemc", "hem@gmail.com", "debater");
            // login1.setUser(user1);
            // LoginDetails login2 = new LoginDetails("harsh456", "harsh", "harsh@gmail.com", "debater");
            // login2.setUser(user2);
            // LoginDetails login3 = new LoginDetails("ameya789", "ameya", "ameya@gmail.com", "debater");
            // login3.setUser(user3);

            // user1.setLoginDet(login1);
            // user2.setLoginDet(login2);
            // user3.setLoginDet(login3);

            // Post post1 = new Post((long)-1,(long)-1,"#abcd","My life");
            // // Post post2 = new Post((long)-1,(long)1,"#abcd","Your life");
            // // post1.setChild((long)2);
            // // Post post3 = new Post((long)-1,(long)-1,"#abcd","Their life");
            // // Post post4 = new Post((long)-1,(long)3,"#abcd","Our life");
            // // post3.setChild((long)4);

            // post1.setUser(user1);
            // post1.setReplyUserId((long)2);
            // post2.setUser(user2);
            // post3.setUser(user3);
            // post4.setUser(user2);
    
            userRepository.saveAllAndFlush(List.of(user1, user2, user3)); 
            //postRepository.saveAllAndFlush(List.of(post1));//, post2, post3, post4));
        };
    }

    public static void main(String[] args) {
        
    }
    
}
