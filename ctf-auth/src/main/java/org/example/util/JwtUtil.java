package org.example.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    // 从配置文件读取JWT密钥（必须至少32字节）
    @Value("${jwt.secret}")
    private String secret;

    // 访问令牌过期时间（单位：秒，如3600=1小时）
    @Value("${jwt.expiration}")
    private Long accessExpirationSeconds;

    // 刷新令牌过期时间（单位：秒，如604800=7天）
    @Value("${jwt.refresh-expiration}")
    private Long refreshExpirationSeconds;

    // 获取签名密钥（并校验长度）
    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        // 校验密钥长度：HS256需要至少256位（32字节）
        if (keyBytes.length < 32) {
            throw new IllegalArgumentException("JWT密钥长度必须至少32字节（256位），当前长度：" + keyBytes.length);
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 在 JwtUtil 类中添加一个新的重载方法
    public static String generateAccessToken(Integer userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        // 调用现有 createToken 方法，设置默认用户名和过期时间
        JwtUtil jwtUtil = new JwtUtil();
        // 注意：此处需确保能获取到 secret 和 accessExpirationSeconds 的值
        // 建议改为通过 Spring 注入实例使用，避免静态方法中无法访问实例变量
        return jwtUtil.createToken(claims, userId.toString(), jwtUtil.accessExpirationSeconds);
    }



    // 生成访问令牌（包含用户权限信息）
    public String generateAccessToken(UserDetails userDetails) {
        // 自定义声明：添加用户权限（如角色）
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        return createToken(claims, userDetails.getUsername(), accessExpirationSeconds);
    }

    // 生成刷新令牌（不包含额外权限，仅用于刷新访问令牌）
    public String generateRefreshToken(UserDetails userDetails) {
        return createToken(new HashMap<>(), userDetails.getUsername(), refreshExpirationSeconds);
    }

    // 核心：创建令牌的通用方法
    private String createToken(Map<String, Object> claims, String subject, Long expirationSeconds) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationSeconds * 1000);

        return Jwts.builder()
                .setClaims(claims)                // 自定义声明
                .setSubject(subject)              // 主题（通常是用户名）
                .setIssuedAt(now)                 // 签发时间
                .setExpiration(expirationDate)    // 过期时间
                .setIssuer("ctf-auth-service")    // 签发者（可选，增强安全性）
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)  // 签名算法
                .compact();
    }

    // 验证令牌是否有效（结合用户信息和令牌状态）
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);
            // 校验：用户名匹配 + 令牌未过期
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (SignatureException e) {
            log.warn("令牌签名无效：{}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.warn("令牌已过期：{}", e.getMessage());
        } catch (JwtException e) {
            log.warn("令牌无效：{}", e.getMessage());
        }
        return false;
    }

    // 从令牌中提取用户名
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 从令牌中提取过期时间
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 提取令牌中的自定义声明（通用方法）
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 解析令牌中的所有声明
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 判断令牌是否过期
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
