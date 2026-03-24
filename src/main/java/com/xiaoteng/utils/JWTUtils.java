package com.xiaoteng.utils;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JWTUtils {
    // 秘钥（最好放在配置文件中）
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_MS = 60 * 60 * 1000; // 1小时
    
    
    /**
     * token的生成方法，涉及到JWT的构建规则
     */
    
    public static String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key)
                .compact();
    }
    
    
    /**
     *
     */
    public static String getUserIdFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    
    /**
     * JWT（JSON Web Token）验证逻辑，用来判断传入的 token 是否有效
     */
    
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false; // 包括过期、签名错误等
        }
    }
    
    public static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
