package cl.nvrrt.cvseguro.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import cl.nvrrt.cvseguro.entities.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
  private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.expirationInMs}")
  private int jwtExpirationMs;

  //
  public String generateToken(String userId) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

    return Jwts.builder()
            .setSubject(userId)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(getSigningKey())
            .compact();
}
  //
  
  private Key getSigningKey(){
    byte [] keyBytes = this.jwtSecret.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  //
  public String getUserNameFromJwtToken(String token) {

    Claims claims = Jwts.parserBuilder()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();

    return claims.getSubject();
  }


  //
  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(jwtSecret).build().parse(authToken);
      return true;
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
}