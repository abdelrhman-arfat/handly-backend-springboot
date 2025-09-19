package com.project.handly.Controllers;

import com.project.handly.DTOs.User.LoginDTO;
import com.project.handly.DTOs.User.OauthDTO;
import com.project.handly.DTOs.User.UserDTO;
import com.project.handly.Entities.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import com.project.handly.Entities.User;
import com.project.handly.Services.UserService;
import com.project.handly.Utils.JwtUtil;
import com.project.handly.Utils.ResponseHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserDTO userDTO, HttpServletResponse response) {
        try {
            User user = userService.register(userDTO);
            ResponseHandler.addJwtCookie(response, user);
            addJwtCookie(response, user);
            return ResponseHandler.success("User registered successfully", user, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseHandler.error("Registration failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO userDTO, HttpServletResponse response) {
        try {
            User user = userService.login(userDTO);
            ResponseHandler.addJwtCookie(response, user);
            addJwtCookie(response, user);
            return ResponseHandler.success("User login successful", user, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.error("Login failed: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    // --------------- GOOGLE Oauth --------------------
    @GetMapping("/google")
    public void redirectToGoogleOauthClient(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }


    // ---------------- HELPER FUNCTION ----------------
    private void addJwtCookie(HttpServletResponse response, User user) {
        String token = jwtUtil.generateToken(user);
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(24 * 60 * 60); // 24 hours
        cookie.setSecure(true);         // HTTPS only
        response.addCookie(cookie);
    }
}
