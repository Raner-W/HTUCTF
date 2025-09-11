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
    @Value("${jwt.expiration:3600}")
    private Long accessExpirationSeconds;

    // 刷新令牌过期时间（单位：秒，如604800=7天）
    @Value("${jwt.refresh-expiration:604800}")
    private Long refreshExpirationSeconds;

    // 获取签名密钥（并校验长度）
    private SecretKey getSigningKey() {
        // 如果secret为空，使用默认值
        String secretKey = secret != null ? secret : "your-32bytes-secure-secret-key-here-12345678";
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        // 校验密钥长度：HS256需要至少256位（32字节）
        if (keyBytes.length < 32) {
            throw new IllegalArgumentException("JWT密钥长度必须至少32字节（256位），当前长度：" + keyBytes.length);
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 生成访问令牌（通过用户ID）
    public String generateAccessToken(Integer userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        // 添加空值检查和默认值
        Long exp = accessExpirationSeconds != null ? accessExpirationSeconds : 3600L;
        return createToken(claims, userId.toString(), exp);
    }

    // 生成访问令牌（包含用户权限信息）
    public String generateAccessToken(UserDetails userDetails) {
        // 自定义声明：添加用户权限（如角色）
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        // 添加空值检查和默认值
        Long exp = accessExpirationSeconds != null ? accessExpirationSeconds : 3600L;
        return createToken(claims, userDetails.getUsername(), exp);
    }

    // 生成刷新令牌（不包含额外权限，仅用于刷新访问令牌）
    public String generateRefreshToken(UserDetails userDetails) {
        // 添加空值检查和默认值
        Long exp = refreshExpirationSeconds != null ? refreshExpirationSeconds : 604800L;
        return createToken(new HashMap<>(), userDetails.getUsername(), exp);
    }

    // 核心：创建令牌的通用方法
    private String createToken(Map<String, Object> claims, String subject, Long expirationSeconds) {
        // 双重保障：确保expirationSeconds不为null
        if (expirationSeconds == null) {
            expirationSeconds = 3600L; // 默认1小时
        }

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
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
