package debate.league.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import debate.league.classes.User;
import debate.league.repositories.UserRepository;
import lombok.NonNull;

@Service
public class UserService {

    @NonNull
    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId){
        return userRepository.findById(userId);
    }    

    public void saveUser(User user){
        userRepository.saveAndFlush(user);
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }


}

