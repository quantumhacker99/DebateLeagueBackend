package debate.league.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import debate.league.classes.LoginDetails;
import debate.league.repositories.LoginDetailsRepository;
import debate.league.repositories.UserRepository;
import lombok.NonNull;

@Service
public class LoginDetailsService implements UserDetailsService {

    @NonNull
    private final LoginDetailsRepository loginDetRepository;

    @Autowired
    public LoginDetailsService(LoginDetailsRepository loginDetRepository){
        this.loginDetRepository = loginDetRepository;
    }

    public List<LoginDetails> getAllDetails(){
        return loginDetRepository.findAll();
    } 

    public Optional<LoginDetails> getDetailsById(String userId){
        return loginDetRepository.findById(userId);
    }


    public void savePost(LoginDetails details){
        loginDetRepository.save(details);
    }

    
	@Autowired
	UserRepository userRepository;
	@Override
	@Transactional
	public LoginDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loginDetRepository.findById(username).get();
	}
}
