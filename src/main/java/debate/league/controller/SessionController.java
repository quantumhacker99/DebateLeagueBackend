package debate.league.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import debate.league.classes.LoginDetails;
import debate.league.classes.Post;
import debate.league.classes.User;
import debate.league.repositories.LoginDetailsRepository;
import debate.league.security.JwtResponse;
import debate.league.security.JwtUtils;
import debate.league.service.LoginDetailsService;
import debate.league.service.PostService;
import debate.league.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@RestController
@CrossOrigin("http://localhost:3000")

public class SessionController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  LoginDetailsRepository loginDetRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  private UserService userService;
  private LoginDetailsService loginDetService;
  private User loggedUser;

  public SessionController(LoginDetailsService loginDetService, UserService userService){
    this.userService = userService;
    this.loginDetService = loginDetService;
    this.loggedUser = null;
}

  public User findDetailsUser(String username){
    List<User> allUsers = userService.getAllUsers();

    User checkUser = null;
    for(User user: allUsers){
        if(user.getLoginDet().getUsername().equals(username)){
            checkUser = user;
        }
    }

    return checkUser;
}



  @RequestMapping(value="/signin", method=RequestMethod.POST)
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    this.loggedUser = findDetailsUser(loginRequest.getUsername());
    
    LoginDetails loginDetails = (LoginDetails) authentication.getPrincipal();    
    //List<String> roles = userDetails.getAuthorities().stream()
    //    .map(item -> item.getAuthority())
    //    .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         this.loggedUser.getUserId(), 
                         loginDetails.getUsername()
                        ));
                         //this.loggedUser.getEmail())); 
                         //roles));
  }

}

@Data
@Getter @Setter @AllArgsConstructor
class LoginRequest{

    private String username;
    private String password;

}