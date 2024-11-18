package com.packt.cardatabase.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtService {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.expire.time}")
    private long expireTime;

    static final String PREFIX = "Bearer";

    public String getToken(String username) {
        String token = Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
        return token;
    }

    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null) {
            String user = Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody().getSubject();
            if (user != null) {
                return user;
            }
        }
        return null;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
