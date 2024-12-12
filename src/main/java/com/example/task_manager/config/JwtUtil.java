package com.example.task_manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*400))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isTokenValid(String token, String username){
        try{
            String extractedUsername = extractUsername(token);
            return(extractedUsername.equals(username) && !isTokenExpired(token));
        } catch (JwtException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token){
        Date expiration = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

}
