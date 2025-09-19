package com.project.handly.Controllers;


import com.project.handly.Entities.User;
import com.project.handly.Repositories.UserRepo;
import com.project.handly.Services.UserService;
import com.project.handly.Utils.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authorized")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/me")
    public ResponseEntity<?> me(HttpServletRequest request) {
        try {
            User user = (User) request.getAttribute("user");
            return ResponseHandler.success("Get user data successfully", user, HttpStatus.OK);
        }catch (Exception e){
            return ResponseHandler.error("Faild to get user profile data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
