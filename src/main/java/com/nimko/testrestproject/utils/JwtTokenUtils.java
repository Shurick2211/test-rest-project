package com.nimko.testrestproject.utils;

import io.jsonwebtoken.Jwts;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtils {

  SecretKey key = Jwts.SIG.HS256.key().build();

  @Value("${jwt.period}")
  private Duration period;

  public String generateToken(UserDetails userDetails){
    Map<String,Object> data = new HashMap<>();
    data.put("name", userDetails.getUsername());
    Date issuedDate = new Date();
    Date expiredDate = new Date(issuedDate.getTime() + period.toMillis());
    return Jwts.builder()
        .subject(userDetails.getUsername())
        .claims(data)
        .issuedAt(issuedDate)
        .expiration(expiredDate)
        .signWith(key)
        .compact();
  }

  public String getUserName(String token){
    return Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
  }
}
