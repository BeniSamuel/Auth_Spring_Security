package com.lock.lock.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "BenI@IsH123";

    public String generateToken ( String email ) {
        return Jwts.builder()
                .setSubject(email)
                .issuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

}
