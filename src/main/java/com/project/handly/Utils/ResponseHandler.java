package com.project.handly.Utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.handly.Entities.Country;
import com.project.handly.Entities.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ResponseHandler {

    private static JwtUtil jwtUtil;

    public ResponseHandler(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public static <T> ResponseEntity<Object> success(String message, T data, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        response.put("data", data);
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<Object> error(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", message);
        response.put("data", null);
        return new ResponseEntity<>(response, status);
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageResponse<T> implements Serializable {
        @JsonProperty("data")
        private List<T> content;

        private int page;

        @JsonProperty("limit")
        private int size;

        @JsonProperty("total")
        private long totalElements;

        private int totalPages;
    }

    public static <T> PageResponse<T> handlePageResponse(Page<T> result) {
        return new PageResponse<>(
                result.getContent(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }

    public static void addJwtCookie(HttpServletResponse response, User user) {
        String token = jwtUtil.generateToken(user);
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(24 * 60 * 60); // 24 hours
        cookie.setSecure(true);         // HTTPS only
        response.addCookie(cookie);
    }


}
