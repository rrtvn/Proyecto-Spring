package cl.nvrrt.cvseguro.config.security.jwt.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JWTUtil {
  private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);

  
  @Value("${jwt.expirationInMs}")
  private int jwtExpirationMs;
  
  private SecretKey secretKey;
  
  // @Value("${jwt.secret}")
  String jwtSecret = "Q1ZTRUdVUk9BUFAtU0VDUkVUSldU";
  
  public JWTUtil() {
    // System.out.println(jwtSecret);
    
    // try {
      
    // } catch (Exception e) {
    //   System.out.println(e.getMessage());
    // }
  }

  @PostConstruct
  public void init(){
    String secret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
      this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }
  


  //
  
  public String generateToken( UserDetails userId) {
    //   Date now = new Date();
    // Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
    // return Jwts.builder()
    //     .subject(userId.getUsername())
    //     .issuedAt(now)
    //     .expiration(expiryDate)
    //     .signWith(Key)
    //     .compact();
    return generateToken(new HashMap<>(), userId);
  }

  public String  generateToken(HashMap<String, Object> claims, UserDetails userDetails) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
    return Jwts.builder()
        .claims(claims)
        .subject(userDetails.getUsername())
        .issuedAt(now)
        .expiration(expiryDate)
        .signWith(secretKey)
        .compact();
  }

  public String extractUsername(String token) {
    return extractClaims(token, Claims::getSubject);
  }

  public boolean isTokenExpired(String token) {
    return extractClaims(token, Claims::getExpiration).before(new Date());

  }

  public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    return claimsResolver.apply(claims);
  }
  public boolean isTokenValid(String token, UserDetails UserDetails) {
    
    final String username = extractUsername(token);
    return (username.equals(UserDetails.getUsername()) && !isTokenExpired(token));
  }
}