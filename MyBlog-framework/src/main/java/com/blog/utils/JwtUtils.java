package com.blog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    public static String createJWT(String userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "liDongSheng")//签名算法
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000 * 12))
                .compact();
        return jwt;
    }
    public static Claims parseJWT(String jwt) throws Exception {
        return Jwts.parser()
                .setSigningKey("liDongSheng")
                .parseClaimsJws(jwt)
                .getBody();
    }
}
