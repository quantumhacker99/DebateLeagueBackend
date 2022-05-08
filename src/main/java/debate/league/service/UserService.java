package debate.league.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import debate.league.classes.LoginDetails;
import debate.league.classes.User;
import debate.league.repositories.UserRepository;
import lombok.NonNull;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService{

    @Autowired
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

	@Override
	@Transactional
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> usersList = this.getAllUsers();
        for(User user:usersList){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
	}

    // public User getUserByLoginDet(LoginDetails logDetails){
    //     List<User> usersList = this.getAllUsers();
    //     System.out.println("Users List is " + usersList);
    //     for(User user:usersList){
    //         //System.out.println("User is " + user.get());
    //         if(user.getLoginDet().equals(logDetails)){
    //                 return user;
    //             }
    //     }
    //     return null;
    // }


}

