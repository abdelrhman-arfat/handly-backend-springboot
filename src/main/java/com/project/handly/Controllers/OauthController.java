package com.project.handly.Controllers;

import com.project.handly.DTOs.User.OauthDTO;
import com.project.handly.Entities.User;
import com.project.handly.Services.UserService;
import com.project.handly.Utils.JwtUtil;
import com.project.handly.Utils.ResponseHandler;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OauthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public OauthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/login/oauth2/google/callback")
    public ResponseEntity<Object> handleGoogleLoginCallback(@AuthenticationPrincipal OAuth2User oAuth2User , HttpServletResponse response) {
     try{
         if (oAuth2User == null) {
             return ResponseHandler.error("Error with login oAuth2User is null", HttpStatus.UNAUTHORIZED);
         }
         String email = oAuth2User.getAttribute("email");
         String name = oAuth2User.getAttribute("name");
         OauthDTO oauthDTO = new OauthDTO(email, name);

         User user = userService.registerOrGetGoogleUser(oauthDTO);
         ResponseHandler.addJwtCookie(response, user);
         // TODO: redirect to the front end page with the cookies
         return ResponseHandler.success("Google login successful", user, HttpStatus.OK);
     }catch(Exception e){
         return ResponseHandler.error("Failed to Login with google" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     }
    }

    @GetMapping("/oauth2/failure")
    public ResponseEntity<Object> failure(){
        return ResponseHandler.error("Failed login with google", HttpStatus.BAD_REQUEST);
    }


}
