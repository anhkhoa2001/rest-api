package com.example.restlibrary.mysql.config;

import com.example.restlibrary.mysql.controller.dto.UserDetailCustomize;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.util.Date;

@Component
public class JwtTokenSetup {

    private String SECRET_KEY;
    private long TIMER;

    public JwtTokenSetup() {
        SECRET_KEY = SystemProperties.getProperty("jwt_key");
        TIMER = Long.parseLong(SystemProperties.getProperty("timer"));
    }

    public String generateToken(UserDetailCustomize user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + TIMER);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String getUsernamFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
