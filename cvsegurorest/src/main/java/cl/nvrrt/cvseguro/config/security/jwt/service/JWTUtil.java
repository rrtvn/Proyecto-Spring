package cl.nvrrt.cvseguro.config.security.jwt.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;


import cl.nvrrt.cvseguro.entities.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTUtil {
  private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.expirationInMs}")
  private int jwtExpirationMs;

  Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  //
  public String generateToken(UserDetails userId) {
    Map<String, Object> claims = new HashMap<>();
    

    return doGenerateToken(claims, userId.getUsername());
  }

  private String  doGenerateToken(Map<String, Object> claims, String subject) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(key).compact();
  }

  public String getUsrFromToken(String token) {
    return getClaimsFromToken(token, Claims::getSubject);
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimsFromToken(token, Claims::getExpiration);

  }

  public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    if (token == null) {
      throw new IllegalArgumentException("token can't be null.");
    }

    try {
      return Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token)
          .getBody();
    } catch (JwtException e) {
      throw new IllegalArgumentException(String.format("Invalid JWT token: %s", e.getMessage()), e);
    }
  }

  //
  public boolean validateJwtToken(String token, UserDetails UserDetails) {
    final String usr = getUsrFromToken(token);
    final Date expiration = getExpirationDateFromToken(token);
    return (usr != null && !usr.isEmpty() && expiration != null && expiration.after(new Date()));

  }
}