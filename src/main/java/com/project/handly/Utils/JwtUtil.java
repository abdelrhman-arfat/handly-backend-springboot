package com.project.handly.Utils;

import com.project.handly.Entities.User;
<<<<<<< HEAD
import com.project.handly.Exceptions.GlobalExceptionHandler;
import com.project.handly.Services.UserService;
=======
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
<<<<<<< HEAD
import java.util.Optional;
import java.util.function.Function;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
=======
import java.util.function.Function;
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
<<<<<<< HEAD
    private final CacheManager cacheManager;
    private final UserService userService;
=======

>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
    private final String secretKey = System.getenv("JWT_SECRET_KEY");
    private final long jwtExpiration = Long.parseLong(
            System.getenv().getOrDefault("JWT_EXPIRATION_MS", "86400000") // default 24h
    );

<<<<<<< HEAD
    public JwtUtil(CacheManager cacheManager , UserService userService) {
        this.cacheManager = cacheManager;
        this.userService = userService;

    }

=======
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
    // ---------------- EXTRACT CLAIMS ----------------
    public Long extractUserId(String token) {
        return ((Number) extractAllClaims(token).get("userId")).longValue();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
<<<<<<< HEAD
    // check if in the cache:
    public Optional<User> extractUserIfTokenInCache(String token) {
        // 1️⃣ تحقق إذا التوكن منتهي
        if (isTokenExpired(token)) {
            throw new GlobalExceptionHandler.UnauthorizedException("Token expired or invalid");
        }

        // 2️⃣ جلب الكاش
        Cache cache = cacheManager.getCache("user_tokens");
        if (cache == null) {
            throw new GlobalExceptionHandler.UnauthorizedException("Token cache not found");
        }

        // 3️⃣ استخراج userId من التوكن
        Long userId = extractUserId(token); // دالة تقوم باستخراج userId من JWT

        // 4️⃣ تحقق إذا التوكن موجود في الكاش
        String cachedToken = cache.get(userId, String.class);
        if (cachedToken == null) {
            throw new GlobalExceptionHandler.UnauthorizedException("Token not in cache");
        }

        // 5️⃣ تحقق إذا التوكن في الكاش يطابق التوكن الحالي
        if (!cachedToken.equals(token)) {
            throw new GlobalExceptionHandler.UnauthorizedException("Token mismatch");
        }

        Optional<User> user = userService.find(userId);
        if (user.isEmpty()) {
            throw new GlobalExceptionHandler.UnauthorizedException("User not found");
        }

        return user;
    }

    // ---------------- GENERATE TOKEN ----------------
    @CachePut(value = "user_tokens", key = "#user.id")
=======

    // ---------------- GENERATE TOKEN ----------------
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        return buildToken(claims);
    }

<<<<<<< HEAD

=======
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
    private String buildToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ---------------- VALIDATE TOKEN ----------------
    public boolean isTokenValid(String token, User user) {
        final Long id = extractUserId(token);
        return id.equals(user.getId()) && !isTokenExpired(token);
    }

    // ---------------- SIGNING KEY ----------------
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
