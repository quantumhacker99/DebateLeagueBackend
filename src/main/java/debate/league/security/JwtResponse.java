package debate.league.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// import java.util.List;

@Getter @Setter @NoArgsConstructor
public class JwtResponse {
  private boolean isNull;
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;

  public JwtResponse(String accessToken, Long id, String username, String email ) { //List<String> roles
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }
}
