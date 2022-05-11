package debate.league.security;


import java.util.Date;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import debate.league.classes.LoginDetails;
import io.jsonwebtoken.*;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

import java.util.Enumeration;
import org.springframework.util.StringUtils;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${jwtSecret}")
  private String jwtSecret;

  @Value("${jwtExpirationMs}")
  private int jwtExpirationMs;

  public String generateJwtToken(String username) {

    //LoginDetails userPrincipal = (LoginDetails) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }

  public String parseJwt (HttpServletRequest request) {

    Enumeration<String> e = request.getHeaderNames();

    while (e.hasMoreElements()) {
      String param = e.nextElement();
      //System.out.println(param);
  }

    String headerAuth = request.getHeader("Authorization");

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7, headerAuth.length());
    }

    return null;
  }

  // protected void jwtFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
  //     throws ServletException, IOException {
  //   try {
  //     String jwt = parseJwt(request);
  //     if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
  //       String username = jwtUtils.getUserNameFromJwtToken(jwt);

  //       UserDetails userDetails = userDetailsService.getDetailsById(username).get();
  //       UsernamePasswordAuthenticationToken authentication =
  //           new UsernamePasswordAuthenticationToken(
  //               userDetails,
  //               null,
  //               userDetails.getAuthorities());
  //       authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

  //       SecurityContextHolder.getContext().setAuthentication(authentication);
  //     }
  //   } catch (Exception e) {
  //     logger.error("Cannot set user authentication: {}", e);
  //   }

  //   filterChain.doFilter(request, response);
  // }





}
