package debate.league.controller;

import java.util.List;
import java.util.Optional;
// import java.lang.Long;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
// import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import debate.league.service.LoginDetailsService;
import debate.league.service.PostService;
import debate.league.service.UserService;
import debate.league.classes.LoginDetails;
import debate.league.classes.User;
import debate.league.security.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@RestController
@CrossOrigin("http://localhost:3000")
public class SessionController {
    
    private PostService postService;
    private UserService userService;
    private LoginDetailsService loginService;
    private User loggedUser;


    @Autowired
    AuthenticationManager authenticationManager;

     @Autowired
     PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


    public SessionController(PostService postService, UserService userService,LoginDetailsService loginService ){
        this.postService = postService;
        this.userService = userService;
        this.loginService = loginService;
        this.loggedUser = null;
    }

    @RequestMapping(value = "/login", method=RequestMethod.POST)
    @CrossOrigin
    public @ResponseBody ResponseEntity<JwtResponse> getToken(@RequestBody LoginDTO dto){

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.getUserID(), dto.getPassword()));
    
        SecurityContextHolder.getContext().setAuthentication(authentication);

        
        this.loggedUser = this.userService.loadUserByUsername(dto.getUserID());
        
        if(loggedUser != null &&(BCrypt.checkpw(dto.getPassword(), loggedUser.getPassword()))){

            System.out.println("Password input is " + dto.getPassword() + " and User Password is " + loggedUser.getPassword());
            System.out.println("Logged User has username " + loggedUser.getUsername());
            System.out.println("passwords are equal or no? - " + BCrypt.checkpw(dto.getPassword(), loggedUser.getPassword()));

            String jwt = jwtUtils.generateJwtToken(this.loggedUser.getUsername());
            JwtResponse response = new JwtResponse(jwt, this.loggedUser.getUserId(), loggedUser.getUsername(), loggedUser.getEmail());
            response.setNull(false);
            return ResponseEntity.ok(response);

        }
        else{
            JwtResponse response = new JwtResponse();
            response.setNull(true);
            return ResponseEntity.ok(response);
        }
        
    
    }
}

@Data
@Getter @Setter @AllArgsConstructor
class LoginDTO{

    private String userID;
    private String password;

}

