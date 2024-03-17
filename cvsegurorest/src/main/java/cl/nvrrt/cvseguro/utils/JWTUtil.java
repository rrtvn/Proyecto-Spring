package cl.nvrrt.cvseguro.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import cl.nvrrt.cvseguro.entities.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.expirationInMs}")
  private int jwtExpirationMs;

  Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  //
  public String generateToken(String userId) {
    Map<String, Object> claims = new HashMap<>();
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

    return Jwts.builder()
            .setClaims(claims)
            .setSubject(userId)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(key).compact();
}
 
  public String getUsrFromToken(String token ){
    return getClaimsFromToken(token, Claims::getSubject);
  }

  public Date getExpirationDateFromToken(String token){
    return getClaimsFromToken(token, Claims::getExpiration);
    
  }

  public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver){
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token ){
    return Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token)
          .getBody();
  }

  

  //
  public boolean validateJwtToken(String token, User user) {
    final String  usr = getUsrFromToken(token);
    final Date expiration = getExpirationDateFromToken(token);
    return (usr != null && !usr.isEmpty() && expiration != null && expiration.after(new Date()));
    
  }
}