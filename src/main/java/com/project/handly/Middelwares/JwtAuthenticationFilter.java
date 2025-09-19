package com.project.handly.Middelwares;

import com.project.handly.Entities.User;
import com.project.handly.Exceptions.GlobalExceptionHandler;
import com.project.handly.Services.UserService;
import com.project.handly.Utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.startsWith("/api/authorized")) {
            // get token from cookie
            String token =  this.getCookieValue(request, "token");
            User user = jwtUtil.extractUserIfTokenInCache(token)
                    .orElseThrow(() -> new GlobalExceptionHandler.UnauthorizedException("Invalid token"));
            request.setAttribute("user", user);
        }
        filterChain.doFilter(request, response);
    }

    private String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new GlobalExceptionHandler.UnauthorizedException("Login first and try again");
        };

        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(name))
                .map(Cookie::getValue)
                .findFirst()
                .orElseThrow(
                        ()-> new GlobalExceptionHandler.UnauthorizedException("Login first and try again")
                );
    }
}
