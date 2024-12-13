package com.cjl.test.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final String Key="njupt";
    public static String genToken(Map<String,Object> claims) {
        return JWT.create()
                .withClaim("claims",claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000*60*60*12))
                .sign(Algorithm.HMAC256(Key));
    }
    public static Map<String,Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(Key))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
